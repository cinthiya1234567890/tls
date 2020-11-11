package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.MalaParam;
import com.neemshade.matri.repository.MalaParamRepository;
import com.neemshade.matri.service.MalaParamService;
import com.neemshade.matri.service.dto.MalaParamDTO;
import com.neemshade.matri.service.mapper.MalaParamMapper;

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
 * Integration tests for the {@link MalaParamResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MalaParamResourceIT {

    private static final Integer DEFAULT_PECK_ORDER = 1;
    private static final Integer UPDATED_PECK_ORDER = 2;

    @Autowired
    private MalaParamRepository malaParamRepository;

    @Autowired
    private MalaParamMapper malaParamMapper;

    @Autowired
    private MalaParamService malaParamService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMalaParamMockMvc;

    private MalaParam malaParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MalaParam createEntity(EntityManager em) {
        MalaParam malaParam = new MalaParam()
            .peckOrder(DEFAULT_PECK_ORDER);
        return malaParam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MalaParam createUpdatedEntity(EntityManager em) {
        MalaParam malaParam = new MalaParam()
            .peckOrder(UPDATED_PECK_ORDER);
        return malaParam;
    }

    @BeforeEach
    public void initTest() {
        malaParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createMalaParam() throws Exception {
        int databaseSizeBeforeCreate = malaParamRepository.findAll().size();
        // Create the MalaParam
        MalaParamDTO malaParamDTO = malaParamMapper.toDto(malaParam);
        restMalaParamMockMvc.perform(post("/api/mala-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaParamDTO)))
            .andExpect(status().isCreated());

        // Validate the MalaParam in the database
        List<MalaParam> malaParamList = malaParamRepository.findAll();
        assertThat(malaParamList).hasSize(databaseSizeBeforeCreate + 1);
        MalaParam testMalaParam = malaParamList.get(malaParamList.size() - 1);
        assertThat(testMalaParam.getPeckOrder()).isEqualTo(DEFAULT_PECK_ORDER);
    }

    @Test
    @Transactional
    public void createMalaParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = malaParamRepository.findAll().size();

        // Create the MalaParam with an existing ID
        malaParam.setId(1L);
        MalaParamDTO malaParamDTO = malaParamMapper.toDto(malaParam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMalaParamMockMvc.perform(post("/api/mala-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MalaParam in the database
        List<MalaParam> malaParamList = malaParamRepository.findAll();
        assertThat(malaParamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMalaParams() throws Exception {
        // Initialize the database
        malaParamRepository.saveAndFlush(malaParam);

        // Get all the malaParamList
        restMalaParamMockMvc.perform(get("/api/mala-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(malaParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].peckOrder").value(hasItem(DEFAULT_PECK_ORDER)));
    }
    
    @Test
    @Transactional
    public void getMalaParam() throws Exception {
        // Initialize the database
        malaParamRepository.saveAndFlush(malaParam);

        // Get the malaParam
        restMalaParamMockMvc.perform(get("/api/mala-params/{id}", malaParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(malaParam.getId().intValue()))
            .andExpect(jsonPath("$.peckOrder").value(DEFAULT_PECK_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingMalaParam() throws Exception {
        // Get the malaParam
        restMalaParamMockMvc.perform(get("/api/mala-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMalaParam() throws Exception {
        // Initialize the database
        malaParamRepository.saveAndFlush(malaParam);

        int databaseSizeBeforeUpdate = malaParamRepository.findAll().size();

        // Update the malaParam
        MalaParam updatedMalaParam = malaParamRepository.findById(malaParam.getId()).get();
        // Disconnect from session so that the updates on updatedMalaParam are not directly saved in db
        em.detach(updatedMalaParam);
        updatedMalaParam
            .peckOrder(UPDATED_PECK_ORDER);
        MalaParamDTO malaParamDTO = malaParamMapper.toDto(updatedMalaParam);

        restMalaParamMockMvc.perform(put("/api/mala-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaParamDTO)))
            .andExpect(status().isOk());

        // Validate the MalaParam in the database
        List<MalaParam> malaParamList = malaParamRepository.findAll();
        assertThat(malaParamList).hasSize(databaseSizeBeforeUpdate);
        MalaParam testMalaParam = malaParamList.get(malaParamList.size() - 1);
        assertThat(testMalaParam.getPeckOrder()).isEqualTo(UPDATED_PECK_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingMalaParam() throws Exception {
        int databaseSizeBeforeUpdate = malaParamRepository.findAll().size();

        // Create the MalaParam
        MalaParamDTO malaParamDTO = malaParamMapper.toDto(malaParam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMalaParamMockMvc.perform(put("/api/mala-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MalaParam in the database
        List<MalaParam> malaParamList = malaParamRepository.findAll();
        assertThat(malaParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMalaParam() throws Exception {
        // Initialize the database
        malaParamRepository.saveAndFlush(malaParam);

        int databaseSizeBeforeDelete = malaParamRepository.findAll().size();

        // Delete the malaParam
        restMalaParamMockMvc.perform(delete("/api/mala-params/{id}", malaParam.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MalaParam> malaParamList = malaParamRepository.findAll();
        assertThat(malaParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
