package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.CascaderParam;
import com.neemshade.matri.repository.CascaderParamRepository;
import com.neemshade.matri.service.CascaderParamService;
import com.neemshade.matri.service.dto.CascaderParamDTO;
import com.neemshade.matri.service.mapper.CascaderParamMapper;

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
 * Integration tests for the {@link CascaderParamResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CascaderParamResourceIT {

    private static final String DEFAULT_PARAM_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PECK_ORDER = 1;
    private static final Integer UPDATED_PECK_ORDER = 2;

    private static final Integer DEFAULT_LEVEL_INDEX = 1;
    private static final Integer UPDATED_LEVEL_INDEX = 2;

    @Autowired
    private CascaderParamRepository cascaderParamRepository;

    @Autowired
    private CascaderParamMapper cascaderParamMapper;

    @Autowired
    private CascaderParamService cascaderParamService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCascaderParamMockMvc;

    private CascaderParam cascaderParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CascaderParam createEntity(EntityManager em) {
        CascaderParam cascaderParam = new CascaderParam()
            .paramTitle(DEFAULT_PARAM_TITLE)
            .peckOrder(DEFAULT_PECK_ORDER)
            .levelIndex(DEFAULT_LEVEL_INDEX);
        return cascaderParam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CascaderParam createUpdatedEntity(EntityManager em) {
        CascaderParam cascaderParam = new CascaderParam()
            .paramTitle(UPDATED_PARAM_TITLE)
            .peckOrder(UPDATED_PECK_ORDER)
            .levelIndex(UPDATED_LEVEL_INDEX);
        return cascaderParam;
    }

    @BeforeEach
    public void initTest() {
        cascaderParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createCascaderParam() throws Exception {
        int databaseSizeBeforeCreate = cascaderParamRepository.findAll().size();
        // Create the CascaderParam
        CascaderParamDTO cascaderParamDTO = cascaderParamMapper.toDto(cascaderParam);
        restCascaderParamMockMvc.perform(post("/api/cascader-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderParamDTO)))
            .andExpect(status().isCreated());

        // Validate the CascaderParam in the database
        List<CascaderParam> cascaderParamList = cascaderParamRepository.findAll();
        assertThat(cascaderParamList).hasSize(databaseSizeBeforeCreate + 1);
        CascaderParam testCascaderParam = cascaderParamList.get(cascaderParamList.size() - 1);
        assertThat(testCascaderParam.getParamTitle()).isEqualTo(DEFAULT_PARAM_TITLE);
        assertThat(testCascaderParam.getPeckOrder()).isEqualTo(DEFAULT_PECK_ORDER);
        assertThat(testCascaderParam.getLevelIndex()).isEqualTo(DEFAULT_LEVEL_INDEX);
    }

    @Test
    @Transactional
    public void createCascaderParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cascaderParamRepository.findAll().size();

        // Create the CascaderParam with an existing ID
        cascaderParam.setId(1L);
        CascaderParamDTO cascaderParamDTO = cascaderParamMapper.toDto(cascaderParam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCascaderParamMockMvc.perform(post("/api/cascader-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CascaderParam in the database
        List<CascaderParam> cascaderParamList = cascaderParamRepository.findAll();
        assertThat(cascaderParamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCascaderParams() throws Exception {
        // Initialize the database
        cascaderParamRepository.saveAndFlush(cascaderParam);

        // Get all the cascaderParamList
        restCascaderParamMockMvc.perform(get("/api/cascader-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cascaderParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].paramTitle").value(hasItem(DEFAULT_PARAM_TITLE)))
            .andExpect(jsonPath("$.[*].peckOrder").value(hasItem(DEFAULT_PECK_ORDER)))
            .andExpect(jsonPath("$.[*].levelIndex").value(hasItem(DEFAULT_LEVEL_INDEX)));
    }
    
    @Test
    @Transactional
    public void getCascaderParam() throws Exception {
        // Initialize the database
        cascaderParamRepository.saveAndFlush(cascaderParam);

        // Get the cascaderParam
        restCascaderParamMockMvc.perform(get("/api/cascader-params/{id}", cascaderParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cascaderParam.getId().intValue()))
            .andExpect(jsonPath("$.paramTitle").value(DEFAULT_PARAM_TITLE))
            .andExpect(jsonPath("$.peckOrder").value(DEFAULT_PECK_ORDER))
            .andExpect(jsonPath("$.levelIndex").value(DEFAULT_LEVEL_INDEX));
    }
    @Test
    @Transactional
    public void getNonExistingCascaderParam() throws Exception {
        // Get the cascaderParam
        restCascaderParamMockMvc.perform(get("/api/cascader-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCascaderParam() throws Exception {
        // Initialize the database
        cascaderParamRepository.saveAndFlush(cascaderParam);

        int databaseSizeBeforeUpdate = cascaderParamRepository.findAll().size();

        // Update the cascaderParam
        CascaderParam updatedCascaderParam = cascaderParamRepository.findById(cascaderParam.getId()).get();
        // Disconnect from session so that the updates on updatedCascaderParam are not directly saved in db
        em.detach(updatedCascaderParam);
        updatedCascaderParam
            .paramTitle(UPDATED_PARAM_TITLE)
            .peckOrder(UPDATED_PECK_ORDER)
            .levelIndex(UPDATED_LEVEL_INDEX);
        CascaderParamDTO cascaderParamDTO = cascaderParamMapper.toDto(updatedCascaderParam);

        restCascaderParamMockMvc.perform(put("/api/cascader-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderParamDTO)))
            .andExpect(status().isOk());

        // Validate the CascaderParam in the database
        List<CascaderParam> cascaderParamList = cascaderParamRepository.findAll();
        assertThat(cascaderParamList).hasSize(databaseSizeBeforeUpdate);
        CascaderParam testCascaderParam = cascaderParamList.get(cascaderParamList.size() - 1);
        assertThat(testCascaderParam.getParamTitle()).isEqualTo(UPDATED_PARAM_TITLE);
        assertThat(testCascaderParam.getPeckOrder()).isEqualTo(UPDATED_PECK_ORDER);
        assertThat(testCascaderParam.getLevelIndex()).isEqualTo(UPDATED_LEVEL_INDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingCascaderParam() throws Exception {
        int databaseSizeBeforeUpdate = cascaderParamRepository.findAll().size();

        // Create the CascaderParam
        CascaderParamDTO cascaderParamDTO = cascaderParamMapper.toDto(cascaderParam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCascaderParamMockMvc.perform(put("/api/cascader-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cascaderParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CascaderParam in the database
        List<CascaderParam> cascaderParamList = cascaderParamRepository.findAll();
        assertThat(cascaderParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCascaderParam() throws Exception {
        // Initialize the database
        cascaderParamRepository.saveAndFlush(cascaderParam);

        int databaseSizeBeforeDelete = cascaderParamRepository.findAll().size();

        // Delete the cascaderParam
        restCascaderParamMockMvc.perform(delete("/api/cascader-params/{id}", cascaderParam.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CascaderParam> cascaderParamList = cascaderParamRepository.findAll();
        assertThat(cascaderParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
