package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.Cascader;
import com.neemshade.matri.repository.CascaderRepository;
import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.service.dto.CascaderDTO;
import com.neemshade.matri.service.mapper.CascaderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CascaderResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CascaderResourceIT {

    private static final String DEFAULT_CASCADER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CASCADER_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CAN_ENTER_CUSTOM_VALUE = false;
    private static final Boolean UPDATED_CAN_ENTER_CUSTOM_VALUE = true;

    @Autowired
    private CascaderRepository cascaderRepository;

    @Autowired
    private CascaderMapper cascaderMapper;

    @Autowired
    private CascaderService cascaderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCascaderMockMvc;

    private Cascader cascader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cascader createEntity(EntityManager em) {
        Cascader cascader = new Cascader()
            .cascaderName(DEFAULT_CASCADER_NAME)
            .canEnterCustomValue(DEFAULT_CAN_ENTER_CUSTOM_VALUE);
        return cascader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cascader createUpdatedEntity(EntityManager em) {
        Cascader cascader = new Cascader()
            .cascaderName(UPDATED_CASCADER_NAME)
            .canEnterCustomValue(UPDATED_CAN_ENTER_CUSTOM_VALUE);
        return cascader;
    }

    @BeforeEach
    public void initTest() {
        cascader = createEntity(em);
    }

    @Test
    @Transactional
    public void createCascader() throws Exception {
        int databaseSizeBeforeCreate = cascaderRepository.findAll().size();
        // Create the Cascader
        CascaderDTO cascaderDTO = cascaderMapper.toDto(cascader);
        restCascaderMockMvc.perform(post("/api/cascaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderDTO)))
            .andExpect(status().isCreated());

        // Validate the Cascader in the database
        List<Cascader> cascaderList = cascaderRepository.findAll();
        assertThat(cascaderList).hasSize(databaseSizeBeforeCreate + 1);
        Cascader testCascader = cascaderList.get(cascaderList.size() - 1);
        assertThat(testCascader.getCascaderName()).isEqualTo(DEFAULT_CASCADER_NAME);
        assertThat(testCascader.isCanEnterCustomValue()).isEqualTo(DEFAULT_CAN_ENTER_CUSTOM_VALUE);
    }

    @Test
    @Transactional
    public void createCascaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cascaderRepository.findAll().size();

        // Create the Cascader with an existing ID
        cascader.setId(1L);
        CascaderDTO cascaderDTO = cascaderMapper.toDto(cascader);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCascaderMockMvc.perform(post("/api/cascaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cascader in the database
        List<Cascader> cascaderList = cascaderRepository.findAll();
        assertThat(cascaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCascaders() throws Exception {
        // Initialize the database
        cascaderRepository.saveAndFlush(cascader);

        // Get all the cascaderList
        restCascaderMockMvc.perform(get("/api/cascaders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cascader.getId().intValue())))
            .andExpect(jsonPath("$.[*].cascaderName").value(hasItem(DEFAULT_CASCADER_NAME)))
            .andExpect(jsonPath("$.[*].canEnterCustomValue").value(hasItem(DEFAULT_CAN_ENTER_CUSTOM_VALUE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCascader() throws Exception {
        // Initialize the database
        cascaderRepository.saveAndFlush(cascader);

        // Get the cascader
        restCascaderMockMvc.perform(get("/api/cascaders/{id}", cascader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cascader.getId().intValue()))
            .andExpect(jsonPath("$.cascaderName").value(DEFAULT_CASCADER_NAME))
            .andExpect(jsonPath("$.canEnterCustomValue").value(DEFAULT_CAN_ENTER_CUSTOM_VALUE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCascader() throws Exception {
        // Get the cascader
        restCascaderMockMvc.perform(get("/api/cascaders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCascader() throws Exception {
        // Initialize the database
        cascaderRepository.saveAndFlush(cascader);

        int databaseSizeBeforeUpdate = cascaderRepository.findAll().size();

        // Update the cascader
        Cascader updatedCascader = cascaderRepository.findById(cascader.getId()).get();
        // Disconnect from session so that the updates on updatedCascader are not directly saved in db
        em.detach(updatedCascader);
        updatedCascader
            .cascaderName(UPDATED_CASCADER_NAME)
            .canEnterCustomValue(UPDATED_CAN_ENTER_CUSTOM_VALUE);
        CascaderDTO cascaderDTO = cascaderMapper.toDto(updatedCascader);

        restCascaderMockMvc.perform(put("/api/cascaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderDTO)))
            .andExpect(status().isOk());

        // Validate the Cascader in the database
        List<Cascader> cascaderList = cascaderRepository.findAll();
        assertThat(cascaderList).hasSize(databaseSizeBeforeUpdate);
        Cascader testCascader = cascaderList.get(cascaderList.size() - 1);
        assertThat(testCascader.getCascaderName()).isEqualTo(UPDATED_CASCADER_NAME);
        assertThat(testCascader.isCanEnterCustomValue()).isEqualTo(UPDATED_CAN_ENTER_CUSTOM_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingCascader() throws Exception {
        int databaseSizeBeforeUpdate = cascaderRepository.findAll().size();

        // Create the Cascader
        CascaderDTO cascaderDTO = cascaderMapper.toDto(cascader);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCascaderMockMvc.perform(put("/api/cascaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cascader in the database
        List<Cascader> cascaderList = cascaderRepository.findAll();
        assertThat(cascaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCascader() throws Exception {
        // Initialize the database
        cascaderRepository.saveAndFlush(cascader);

        int databaseSizeBeforeDelete = cascaderRepository.findAll().size();

        // Delete the cascader
        restCascaderMockMvc.perform(delete("/api/cascaders/{id}", cascader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cascader> cascaderList = cascaderRepository.findAll();
        assertThat(cascaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
