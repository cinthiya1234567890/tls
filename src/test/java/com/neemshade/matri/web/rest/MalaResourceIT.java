package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.Mala;
import com.neemshade.matri.repository.MalaRepository;
import com.neemshade.matri.service.MalaService;
import com.neemshade.matri.service.dto.MalaDTO;
import com.neemshade.matri.service.mapper.MalaMapper;

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
 * Integration tests for the {@link MalaResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MalaResourceIT {

    private static final String DEFAULT_MALA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MALA_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_EDITABLE = false;
    private static final Boolean UPDATED_IS_EDITABLE = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MalaRepository malaRepository;

    @Autowired
    private MalaMapper malaMapper;

    @Autowired
    private MalaService malaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMalaMockMvc;

    private Mala mala;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mala createEntity(EntityManager em) {
        Mala mala = new Mala()
            .malaName(DEFAULT_MALA_NAME)
            .isEditable(DEFAULT_IS_EDITABLE)
            .description(DEFAULT_DESCRIPTION);
        return mala;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mala createUpdatedEntity(EntityManager em) {
        Mala mala = new Mala()
            .malaName(UPDATED_MALA_NAME)
            .isEditable(UPDATED_IS_EDITABLE)
            .description(UPDATED_DESCRIPTION);
        return mala;
    }

    @BeforeEach
    public void initTest() {
        mala = createEntity(em);
    }

    @Test
    @Transactional
    public void createMala() throws Exception {
        int databaseSizeBeforeCreate = malaRepository.findAll().size();
        // Create the Mala
        MalaDTO malaDTO = malaMapper.toDto(mala);
        restMalaMockMvc.perform(post("/api/malas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaDTO)))
            .andExpect(status().isCreated());

        // Validate the Mala in the database
        List<Mala> malaList = malaRepository.findAll();
        assertThat(malaList).hasSize(databaseSizeBeforeCreate + 1);
        Mala testMala = malaList.get(malaList.size() - 1);
        assertThat(testMala.getMalaName()).isEqualTo(DEFAULT_MALA_NAME);
        assertThat(testMala.isIsEditable()).isEqualTo(DEFAULT_IS_EDITABLE);
        assertThat(testMala.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMalaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = malaRepository.findAll().size();

        // Create the Mala with an existing ID
        mala.setId(1L);
        MalaDTO malaDTO = malaMapper.toDto(mala);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMalaMockMvc.perform(post("/api/malas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mala in the database
        List<Mala> malaList = malaRepository.findAll();
        assertThat(malaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMalas() throws Exception {
        // Initialize the database
        malaRepository.saveAndFlush(mala);

        // Get all the malaList
        restMalaMockMvc.perform(get("/api/malas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mala.getId().intValue())))
            .andExpect(jsonPath("$.[*].malaName").value(hasItem(DEFAULT_MALA_NAME)))
            .andExpect(jsonPath("$.[*].isEditable").value(hasItem(DEFAULT_IS_EDITABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getMala() throws Exception {
        // Initialize the database
        malaRepository.saveAndFlush(mala);

        // Get the mala
        restMalaMockMvc.perform(get("/api/malas/{id}", mala.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mala.getId().intValue()))
            .andExpect(jsonPath("$.malaName").value(DEFAULT_MALA_NAME))
            .andExpect(jsonPath("$.isEditable").value(DEFAULT_IS_EDITABLE.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingMala() throws Exception {
        // Get the mala
        restMalaMockMvc.perform(get("/api/malas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMala() throws Exception {
        // Initialize the database
        malaRepository.saveAndFlush(mala);

        int databaseSizeBeforeUpdate = malaRepository.findAll().size();

        // Update the mala
        Mala updatedMala = malaRepository.findById(mala.getId()).get();
        // Disconnect from session so that the updates on updatedMala are not directly saved in db
        em.detach(updatedMala);
        updatedMala
            .malaName(UPDATED_MALA_NAME)
            .isEditable(UPDATED_IS_EDITABLE)
            .description(UPDATED_DESCRIPTION);
        MalaDTO malaDTO = malaMapper.toDto(updatedMala);

        restMalaMockMvc.perform(put("/api/malas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaDTO)))
            .andExpect(status().isOk());

        // Validate the Mala in the database
        List<Mala> malaList = malaRepository.findAll();
        assertThat(malaList).hasSize(databaseSizeBeforeUpdate);
        Mala testMala = malaList.get(malaList.size() - 1);
        assertThat(testMala.getMalaName()).isEqualTo(UPDATED_MALA_NAME);
        assertThat(testMala.isIsEditable()).isEqualTo(UPDATED_IS_EDITABLE);
        assertThat(testMala.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMala() throws Exception {
        int databaseSizeBeforeUpdate = malaRepository.findAll().size();

        // Create the Mala
        MalaDTO malaDTO = malaMapper.toDto(mala);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMalaMockMvc.perform(put("/api/malas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(malaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mala in the database
        List<Mala> malaList = malaRepository.findAll();
        assertThat(malaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMala() throws Exception {
        // Initialize the database
        malaRepository.saveAndFlush(mala);

        int databaseSizeBeforeDelete = malaRepository.findAll().size();

        // Delete the mala
        restMalaMockMvc.perform(delete("/api/malas/{id}", mala.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mala> malaList = malaRepository.findAll();
        assertThat(malaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
