package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.Query;
import com.neemshade.matri.repository.QueryRepository;
import com.neemshade.matri.service.QueryService;
import com.neemshade.matri.service.dto.QueryDTO;
import com.neemshade.matri.service.mapper.QueryMapper;

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
 * Integration tests for the {@link QueryResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QueryResourceIT {

    private static final String DEFAULT_QUERY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_QUERY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PECK_ORDER = 1;
    private static final Integer UPDATED_PECK_ORDER = 2;

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private QueryMapper queryMapper;

    @Autowired
    private QueryService queryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQueryMockMvc;

    private Query query;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Query createEntity(EntityManager em) {
        Query query = new Query()
            .queryName(DEFAULT_QUERY_NAME)
            .peckOrder(DEFAULT_PECK_ORDER);
        return query;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Query createUpdatedEntity(EntityManager em) {
        Query query = new Query()
            .queryName(UPDATED_QUERY_NAME)
            .peckOrder(UPDATED_PECK_ORDER);
        return query;
    }

    @BeforeEach
    public void initTest() {
        query = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuery() throws Exception {
        int databaseSizeBeforeCreate = queryRepository.findAll().size();
        // Create the Query
        QueryDTO queryDTO = queryMapper.toDto(query);
        restQueryMockMvc.perform(post("/api/queries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryDTO)))
            .andExpect(status().isCreated());

        // Validate the Query in the database
        List<Query> queryList = queryRepository.findAll();
        assertThat(queryList).hasSize(databaseSizeBeforeCreate + 1);
        Query testQuery = queryList.get(queryList.size() - 1);
        assertThat(testQuery.getQueryName()).isEqualTo(DEFAULT_QUERY_NAME);
        assertThat(testQuery.getPeckOrder()).isEqualTo(DEFAULT_PECK_ORDER);
    }

    @Test
    @Transactional
    public void createQueryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = queryRepository.findAll().size();

        // Create the Query with an existing ID
        query.setId(1L);
        QueryDTO queryDTO = queryMapper.toDto(query);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQueryMockMvc.perform(post("/api/queries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Query in the database
        List<Query> queryList = queryRepository.findAll();
        assertThat(queryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQueries() throws Exception {
        // Initialize the database
        queryRepository.saveAndFlush(query);

        // Get all the queryList
        restQueryMockMvc.perform(get("/api/queries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(query.getId().intValue())))
            .andExpect(jsonPath("$.[*].queryName").value(hasItem(DEFAULT_QUERY_NAME)))
            .andExpect(jsonPath("$.[*].peckOrder").value(hasItem(DEFAULT_PECK_ORDER)));
    }
    
    @Test
    @Transactional
    public void getQuery() throws Exception {
        // Initialize the database
        queryRepository.saveAndFlush(query);

        // Get the query
        restQueryMockMvc.perform(get("/api/queries/{id}", query.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(query.getId().intValue()))
            .andExpect(jsonPath("$.queryName").value(DEFAULT_QUERY_NAME))
            .andExpect(jsonPath("$.peckOrder").value(DEFAULT_PECK_ORDER));
    }
    @Test
    @Transactional
    public void getNonExistingQuery() throws Exception {
        // Get the query
        restQueryMockMvc.perform(get("/api/queries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuery() throws Exception {
        // Initialize the database
        queryRepository.saveAndFlush(query);

        int databaseSizeBeforeUpdate = queryRepository.findAll().size();

        // Update the query
        Query updatedQuery = queryRepository.findById(query.getId()).get();
        // Disconnect from session so that the updates on updatedQuery are not directly saved in db
        em.detach(updatedQuery);
        updatedQuery
            .queryName(UPDATED_QUERY_NAME)
            .peckOrder(UPDATED_PECK_ORDER);
        QueryDTO queryDTO = queryMapper.toDto(updatedQuery);

        restQueryMockMvc.perform(put("/api/queries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryDTO)))
            .andExpect(status().isOk());

        // Validate the Query in the database
        List<Query> queryList = queryRepository.findAll();
        assertThat(queryList).hasSize(databaseSizeBeforeUpdate);
        Query testQuery = queryList.get(queryList.size() - 1);
        assertThat(testQuery.getQueryName()).isEqualTo(UPDATED_QUERY_NAME);
        assertThat(testQuery.getPeckOrder()).isEqualTo(UPDATED_PECK_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingQuery() throws Exception {
        int databaseSizeBeforeUpdate = queryRepository.findAll().size();

        // Create the Query
        QueryDTO queryDTO = queryMapper.toDto(query);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQueryMockMvc.perform(put("/api/queries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(queryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Query in the database
        List<Query> queryList = queryRepository.findAll();
        assertThat(queryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuery() throws Exception {
        // Initialize the database
        queryRepository.saveAndFlush(query);

        int databaseSizeBeforeDelete = queryRepository.findAll().size();

        // Delete the query
        restQueryMockMvc.perform(delete("/api/queries/{id}", query.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Query> queryList = queryRepository.findAll();
        assertThat(queryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
