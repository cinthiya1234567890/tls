package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.PlateParam;
import com.neemshade.matri.repository.PlateParamRepository;
import com.neemshade.matri.service.PlateParamService;
import com.neemshade.matri.service.dto.PlateParamDTO;
import com.neemshade.matri.service.mapper.PlateParamMapper;

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
 * Integration tests for the {@link PlateParamResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlateParamResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PECK_ORDER = 1;
    private static final Integer UPDATED_PECK_ORDER = 2;

    @Autowired
    private PlateParamRepository plateParamRepository;

    @Autowired
    private PlateParamMapper plateParamMapper;

    @Autowired
    private PlateParamService plateParamService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlateParamMockMvc;

    private PlateParam plateParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlateParam createEntity(EntityManager em) {
        PlateParam plateParam = new PlateParam()
            .value(DEFAULT_VALUE)
            .peckOrder(DEFAULT_PECK_ORDER);
        return plateParam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlateParam createUpdatedEntity(EntityManager em) {
        PlateParam plateParam = new PlateParam()
            .value(UPDATED_VALUE)
            .peckOrder(UPDATED_PECK_ORDER);
        return plateParam;
    }

    @BeforeEach
    public void initTest() {
        plateParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlateParam() throws Exception {
        int databaseSizeBeforeCreate = plateParamRepository.findAll().size();
        // Create the PlateParam
        PlateParamDTO plateParamDTO = plateParamMapper.toDto(plateParam);
        restPlateParamMockMvc.perform(post("/api/plate-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plateParamDTO)))
            .andExpect(status().isCreated());

        // Validate the PlateParam in the database
        List<PlateParam> plateParamList = plateParamRepository.findAll();
        assertThat(plateParamList).hasSize(databaseSizeBeforeCreate + 1);
        PlateParam testPlateParam = plateParamList.get(plateParamList.size() - 1);
        assertThat(testPlateParam.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testPlateParam.getPeckOrder()).isEqualTo(DEFAULT_PECK_ORDER);
    }

    @Test
    @Transactional
    public void createPlateParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = plateParamRepository.findAll().size();

        // Create the PlateParam with an existing ID
        plateParam.setId(1L);
        PlateParamDTO plateParamDTO = plateParamMapper.toDto(plateParam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlateParamMockMvc.perform(post("/api/plate-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plateParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlateParam in the database
        List<PlateParam> plateParamList = plateParamRepository.findAll();
        assertThat(plateParamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlateParams() throws Exception {
        // Initialize the database
        plateParamRepository.saveAndFlush(plateParam);

        // Get all the plateParamList
        restPlateParamMockMvc.perform(get("/api/plate-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plateParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].peckOrder").value(hasItem(DEFAULT_PECK_ORDER)));
    }
    
    @Test
    @Transactional
    public void getPlateParam() throws Exception {
        // Initialize the database
        plateParamRepository.saveAndFlush(plateParam);

        // Get the plateParam
        restPlateParamMockMvc.perform(get("/api/plate-params/{id}", plateParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(plateParam.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.peckOrder").value(DEFAULT_PECK_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingPlateParam() throws Exception {
        // Get the plateParam
        restPlateParamMockMvc.perform(get("/api/plate-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlateParam() throws Exception {
        // Initialize the database
        plateParamRepository.saveAndFlush(plateParam);

        int databaseSizeBeforeUpdate = plateParamRepository.findAll().size();

        // Update the plateParam
        PlateParam updatedPlateParam = plateParamRepository.findById(plateParam.getId()).get();
        // Disconnect from session so that the updates on updatedPlateParam are not directly saved in db
        em.detach(updatedPlateParam);
        updatedPlateParam
            .value(UPDATED_VALUE)
            .peckOrder(UPDATED_PECK_ORDER);
        PlateParamDTO plateParamDTO = plateParamMapper.toDto(updatedPlateParam);

        restPlateParamMockMvc.perform(put("/api/plate-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plateParamDTO)))
            .andExpect(status().isOk());

        // Validate the PlateParam in the database
        List<PlateParam> plateParamList = plateParamRepository.findAll();
        assertThat(plateParamList).hasSize(databaseSizeBeforeUpdate);
        PlateParam testPlateParam = plateParamList.get(plateParamList.size() - 1);
        assertThat(testPlateParam.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPlateParam.getPeckOrder()).isEqualTo(UPDATED_PECK_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingPlateParam() throws Exception {
        int databaseSizeBeforeUpdate = plateParamRepository.findAll().size();

        // Create the PlateParam
        PlateParamDTO plateParamDTO = plateParamMapper.toDto(plateParam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlateParamMockMvc.perform(put("/api/plate-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plateParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlateParam in the database
        List<PlateParam> plateParamList = plateParamRepository.findAll();
        assertThat(plateParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlateParam() throws Exception {
        // Initialize the database
        plateParamRepository.saveAndFlush(plateParam);

        int databaseSizeBeforeDelete = plateParamRepository.findAll().size();

        // Delete the plateParam
        restPlateParamMockMvc.perform(delete("/api/plate-params/{id}", plateParam.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlateParam> plateParamList = plateParamRepository.findAll();
        assertThat(plateParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
