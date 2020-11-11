package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.QueryPlate;
import com.neemshade.matri.repository.QueryPlateRepository;
import com.neemshade.matri.service.QueryPlateService;
import com.neemshade.matri.service.dto.QueryPlateDTO;
import com.neemshade.matri.service.mapper.QueryPlateMapper;

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
 * Integration tests for the {@link QueryPlateResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QueryPlateResourceIT {

    private static final Boolean DEFAULT_IS_RANGE = false;
    private static final Boolean UPDATED_IS_RANGE = true;

    private static final Boolean DEFAULT_IS_MULTI = false;
    private static final Boolean UPDATED_IS_MULTI = true;

    private static final Integer DEFAULT_PECK_ORDER = 1;
    private static final Integer UPDATED_PECK_ORDER = 2;

    @Autowired
    private QueryPlateRepository queryPlateRepository;

    @Autowired
    private QueryPlateMapper queryPlateMapper;

    @Autowired
    private QueryPlateService queryPlateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQueryPlateMockMvc;

    private QueryPlate queryPlate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QueryPlate createEntity(EntityManager em) {
        QueryPlate queryPlate = new QueryPlate()
            .isRange(DEFAULT_IS_RANGE)
            .isMulti(DEFAULT_IS_MULTI)
            .peckOrder(DEFAULT_PECK_ORDER);
        return queryPlate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QueryPlate createUpdatedEntity(EntityManager em) {
        QueryPlate queryPlate = new QueryPlate()
            .isRange(UPDATED_IS_RANGE)
            .isMulti(UPDATED_IS_MULTI)
            .peckOrder(UPDATED_PECK_ORDER);
        return queryPlate;
    }

    @BeforeEach
    public void initTest() {
        queryPlate = createEntity(em);
    }

    @Test
    @Transactional
    public void createQueryPlate() throws Exception {
        int databaseSizeBeforeCreate = queryPlateRepository.findAll().size();
        // Create the QueryPlate
        QueryPlateDTO queryPlateDTO = queryPlateMapper.toDto(queryPlate);
        restQueryPlateMockMvc.perform(post("/api/query-plates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryPlateDTO)))
            .andExpect(status().isCreated());

        // Validate the QueryPlate in the database
        List<QueryPlate> queryPlateList = queryPlateRepository.findAll();
        assertThat(queryPlateList).hasSize(databaseSizeBeforeCreate + 1);
        QueryPlate testQueryPlate = queryPlateList.get(queryPlateList.size() - 1);
        assertThat(testQueryPlate.isIsRange()).isEqualTo(DEFAULT_IS_RANGE);
        assertThat(testQueryPlate.isIsMulti()).isEqualTo(DEFAULT_IS_MULTI);
        assertThat(testQueryPlate.getPeckOrder()).isEqualTo(DEFAULT_PECK_ORDER);
    }

    @Test
    @Transactional
    public void createQueryPlateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = queryPlateRepository.findAll().size();

        // Create the QueryPlate with an existing ID
        queryPlate.setId(1L);
        QueryPlateDTO queryPlateDTO = queryPlateMapper.toDto(queryPlate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQueryPlateMockMvc.perform(post("/api/query-plates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryPlateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QueryPlate in the database
        List<QueryPlate> queryPlateList = queryPlateRepository.findAll();
        assertThat(queryPlateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQueryPlates() throws Exception {
        // Initialize the database
        queryPlateRepository.saveAndFlush(queryPlate);

        // Get all the queryPlateList
        restQueryPlateMockMvc.perform(get("/api/query-plates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(queryPlate.getId().intValue())))
            .andExpect(jsonPath("$.[*].isRange").value(hasItem(DEFAULT_IS_RANGE.booleanValue())))
            .andExpect(jsonPath("$.[*].isMulti").value(hasItem(DEFAULT_IS_MULTI.booleanValue())))
            .andExpect(jsonPath("$.[*].peckOrder").value(hasItem(DEFAULT_PECK_ORDER)));
    }
    
    @Test
    @Transactional
    public void getQueryPlate() throws Exception {
        // Initialize the database
        queryPlateRepository.saveAndFlush(queryPlate);

        // Get the queryPlate
        restQueryPlateMockMvc.perform(get("/api/query-plates/{id}", queryPlate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(queryPlate.getId().intValue()))
            .andExpect(jsonPath("$.isRange").value(DEFAULT_IS_RANGE.booleanValue()))
            .andExpect(jsonPath("$.isMulti").value(DEFAULT_IS_MULTI.booleanValue()))
            .andExpect(jsonPath("$.peckOrder").value(DEFAULT_PECK_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingQueryPlate() throws Exception {
        // Get the queryPlate
        restQueryPlateMockMvc.perform(get("/api/query-plates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQueryPlate() throws Exception {
        // Initialize the database
        queryPlateRepository.saveAndFlush(queryPlate);

        int databaseSizeBeforeUpdate = queryPlateRepository.findAll().size();

        // Update the queryPlate
        QueryPlate updatedQueryPlate = queryPlateRepository.findById(queryPlate.getId()).get();
        // Disconnect from session so that the updates on updatedQueryPlate are not directly saved in db
        em.detach(updatedQueryPlate);
        updatedQueryPlate
            .isRange(UPDATED_IS_RANGE)
            .isMulti(UPDATED_IS_MULTI)
            .peckOrder(UPDATED_PECK_ORDER);
        QueryPlateDTO queryPlateDTO = queryPlateMapper.toDto(updatedQueryPlate);

        restQueryPlateMockMvc.perform(put("/api/query-plates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryPlateDTO)))
            .andExpect(status().isOk());

        // Validate the QueryPlate in the database
        List<QueryPlate> queryPlateList = queryPlateRepository.findAll();
        assertThat(queryPlateList).hasSize(databaseSizeBeforeUpdate);
        QueryPlate testQueryPlate = queryPlateList.get(queryPlateList.size() - 1);
        assertThat(testQueryPlate.isIsRange()).isEqualTo(UPDATED_IS_RANGE);
        assertThat(testQueryPlate.isIsMulti()).isEqualTo(UPDATED_IS_MULTI);
        assertThat(testQueryPlate.getPeckOrder()).isEqualTo(UPDATED_PECK_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingQueryPlate() throws Exception {
        int databaseSizeBeforeUpdate = queryPlateRepository.findAll().size();

        // Create the QueryPlate
        QueryPlateDTO queryPlateDTO = queryPlateMapper.toDto(queryPlate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQueryPlateMockMvc.perform(put("/api/query-plates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryPlateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QueryPlate in the database
        List<QueryPlate> queryPlateList = queryPlateRepository.findAll();
        assertThat(queryPlateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQueryPlate() throws Exception {
        // Initialize the database
        queryPlateRepository.saveAndFlush(queryPlate);

        int databaseSizeBeforeDelete = queryPlateRepository.findAll().size();

        // Delete the queryPlate
        restQueryPlateMockMvc.perform(delete("/api/query-plates/{id}", queryPlate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QueryPlate> queryPlateList = queryPlateRepository.findAll();
        assertThat(queryPlateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
