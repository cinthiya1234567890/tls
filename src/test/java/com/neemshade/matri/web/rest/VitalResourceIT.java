package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.Vital;
import com.neemshade.matri.repository.VitalRepository;
import com.neemshade.matri.service.VitalService;
import com.neemshade.matri.service.dto.VitalDTO;
import com.neemshade.matri.service.mapper.VitalMapper;

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
 * Integration tests for the {@link VitalResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VitalResourceIT {

    private static final String DEFAULT_VITAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VITAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VITAL_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VITAL_VALUE = "BBBBBBBBBB";

    @Autowired
    private VitalRepository vitalRepository;

    @Autowired
    private VitalMapper vitalMapper;

    @Autowired
    private VitalService vitalService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVitalMockMvc;

    private Vital vital;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vital createEntity(EntityManager em) {
        Vital vital = new Vital()
            .vitalName(DEFAULT_VITAL_NAME)
            .vitalValue(DEFAULT_VITAL_VALUE);
        return vital;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vital createUpdatedEntity(EntityManager em) {
        Vital vital = new Vital()
            .vitalName(UPDATED_VITAL_NAME)
            .vitalValue(UPDATED_VITAL_VALUE);
        return vital;
    }

    @BeforeEach
    public void initTest() {
        vital = createEntity(em);
    }

    @Test
    @Transactional
    public void createVital() throws Exception {
        int databaseSizeBeforeCreate = vitalRepository.findAll().size();
        // Create the Vital
        VitalDTO vitalDTO = vitalMapper.toDto(vital);
        restVitalMockMvc.perform(post("/api/vitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vitalDTO)))
            .andExpect(status().isCreated());

        // Validate the Vital in the database
        List<Vital> vitalList = vitalRepository.findAll();
        assertThat(vitalList).hasSize(databaseSizeBeforeCreate + 1);
        Vital testVital = vitalList.get(vitalList.size() - 1);
        assertThat(testVital.getVitalName()).isEqualTo(DEFAULT_VITAL_NAME);
        assertThat(testVital.getVitalValue()).isEqualTo(DEFAULT_VITAL_VALUE);
    }

    @Test
    @Transactional
    public void createVitalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vitalRepository.findAll().size();

        // Create the Vital with an existing ID
        vital.setId(1L);
        VitalDTO vitalDTO = vitalMapper.toDto(vital);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVitalMockMvc.perform(post("/api/vitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vitalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vital in the database
        List<Vital> vitalList = vitalRepository.findAll();
        assertThat(vitalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVitals() throws Exception {
        // Initialize the database
        vitalRepository.saveAndFlush(vital);

        // Get all the vitalList
        restVitalMockMvc.perform(get("/api/vitals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vital.getId().intValue())))
            .andExpect(jsonPath("$.[*].vitalName").value(hasItem(DEFAULT_VITAL_NAME)))
            .andExpect(jsonPath("$.[*].vitalValue").value(hasItem(DEFAULT_VITAL_VALUE)));
    }
    
    @Test
    @Transactional
    public void getVital() throws Exception {
        // Initialize the database
        vitalRepository.saveAndFlush(vital);

        // Get the vital
        restVitalMockMvc.perform(get("/api/vitals/{id}", vital.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vital.getId().intValue()))
            .andExpect(jsonPath("$.vitalName").value(DEFAULT_VITAL_NAME))
            .andExpect(jsonPath("$.vitalValue").value(DEFAULT_VITAL_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingVital() throws Exception {
        // Get the vital
        restVitalMockMvc.perform(get("/api/vitals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVital() throws Exception {
        // Initialize the database
        vitalRepository.saveAndFlush(vital);

        int databaseSizeBeforeUpdate = vitalRepository.findAll().size();

        // Update the vital
        Vital updatedVital = vitalRepository.findById(vital.getId()).get();
        // Disconnect from session so that the updates on updatedVital are not directly saved in db
        em.detach(updatedVital);
        updatedVital
            .vitalName(UPDATED_VITAL_NAME)
            .vitalValue(UPDATED_VITAL_VALUE);
        VitalDTO vitalDTO = vitalMapper.toDto(updatedVital);

        restVitalMockMvc.perform(put("/api/vitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vitalDTO)))
            .andExpect(status().isOk());

        // Validate the Vital in the database
        List<Vital> vitalList = vitalRepository.findAll();
        assertThat(vitalList).hasSize(databaseSizeBeforeUpdate);
        Vital testVital = vitalList.get(vitalList.size() - 1);
        assertThat(testVital.getVitalName()).isEqualTo(UPDATED_VITAL_NAME);
        assertThat(testVital.getVitalValue()).isEqualTo(UPDATED_VITAL_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingVital() throws Exception {
        int databaseSizeBeforeUpdate = vitalRepository.findAll().size();

        // Create the Vital
        VitalDTO vitalDTO = vitalMapper.toDto(vital);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVitalMockMvc.perform(put("/api/vitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vitalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vital in the database
        List<Vital> vitalList = vitalRepository.findAll();
        assertThat(vitalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVital() throws Exception {
        // Initialize the database
        vitalRepository.saveAndFlush(vital);

        int databaseSizeBeforeDelete = vitalRepository.findAll().size();

        // Delete the vital
        restVitalMockMvc.perform(delete("/api/vitals/{id}", vital.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vital> vitalList = vitalRepository.findAll();
        assertThat(vitalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
