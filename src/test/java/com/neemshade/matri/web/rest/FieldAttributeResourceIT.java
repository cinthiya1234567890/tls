package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.FieldAttribute;
import com.neemshade.matri.repository.FieldAttributeRepository;
import com.neemshade.matri.service.FieldAttributeService;
import com.neemshade.matri.service.dto.FieldAttributeDTO;
import com.neemshade.matri.service.mapper.FieldAttributeMapper;

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
 * Integration tests for the {@link FieldAttributeResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FieldAttributeResourceIT {

    private static final String DEFAULT_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_VALUE = "BBBBBBBBBB";

    @Autowired
    private FieldAttributeRepository fieldAttributeRepository;

    @Autowired
    private FieldAttributeMapper fieldAttributeMapper;

    @Autowired
    private FieldAttributeService fieldAttributeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFieldAttributeMockMvc;

    private FieldAttribute fieldAttribute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldAttribute createEntity(EntityManager em) {
        FieldAttribute fieldAttribute = new FieldAttribute()
            .attributeName(DEFAULT_ATTRIBUTE_NAME)
            .attributeValue(DEFAULT_ATTRIBUTE_VALUE);
        return fieldAttribute;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FieldAttribute createUpdatedEntity(EntityManager em) {
        FieldAttribute fieldAttribute = new FieldAttribute()
            .attributeName(UPDATED_ATTRIBUTE_NAME)
            .attributeValue(UPDATED_ATTRIBUTE_VALUE);
        return fieldAttribute;
    }

    @BeforeEach
    public void initTest() {
        fieldAttribute = createEntity(em);
    }

    @Test
    @Transactional
    public void createFieldAttribute() throws Exception {
        int databaseSizeBeforeCreate = fieldAttributeRepository.findAll().size();
        // Create the FieldAttribute
        FieldAttributeDTO fieldAttributeDTO = fieldAttributeMapper.toDto(fieldAttribute);
        restFieldAttributeMockMvc.perform(post("/api/field-attributes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldAttributeDTO)))
            .andExpect(status().isCreated());

        // Validate the FieldAttribute in the database
        List<FieldAttribute> fieldAttributeList = fieldAttributeRepository.findAll();
        assertThat(fieldAttributeList).hasSize(databaseSizeBeforeCreate + 1);
        FieldAttribute testFieldAttribute = fieldAttributeList.get(fieldAttributeList.size() - 1);
        assertThat(testFieldAttribute.getAttributeName()).isEqualTo(DEFAULT_ATTRIBUTE_NAME);
        assertThat(testFieldAttribute.getAttributeValue()).isEqualTo(DEFAULT_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createFieldAttributeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fieldAttributeRepository.findAll().size();

        // Create the FieldAttribute with an existing ID
        fieldAttribute.setId(1L);
        FieldAttributeDTO fieldAttributeDTO = fieldAttributeMapper.toDto(fieldAttribute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFieldAttributeMockMvc.perform(post("/api/field-attributes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldAttributeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FieldAttribute in the database
        List<FieldAttribute> fieldAttributeList = fieldAttributeRepository.findAll();
        assertThat(fieldAttributeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFieldAttributes() throws Exception {
        // Initialize the database
        fieldAttributeRepository.saveAndFlush(fieldAttribute);

        // Get all the fieldAttributeList
        restFieldAttributeMockMvc.perform(get("/api/field-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fieldAttribute.getId().intValue())))
            .andExpect(jsonPath("$.[*].attributeName").value(hasItem(DEFAULT_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].attributeValue").value(hasItem(DEFAULT_ATTRIBUTE_VALUE)));
    }
    
    @Test
    @Transactional
    public void getFieldAttribute() throws Exception {
        // Initialize the database
        fieldAttributeRepository.saveAndFlush(fieldAttribute);

        // Get the fieldAttribute
        restFieldAttributeMockMvc.perform(get("/api/field-attributes/{id}", fieldAttribute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fieldAttribute.getId().intValue()))
            .andExpect(jsonPath("$.attributeName").value(DEFAULT_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.attributeValue").value(DEFAULT_ATTRIBUTE_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingFieldAttribute() throws Exception {
        // Get the fieldAttribute
        restFieldAttributeMockMvc.perform(get("/api/field-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFieldAttribute() throws Exception {
        // Initialize the database
        fieldAttributeRepository.saveAndFlush(fieldAttribute);

        int databaseSizeBeforeUpdate = fieldAttributeRepository.findAll().size();

        // Update the fieldAttribute
        FieldAttribute updatedFieldAttribute = fieldAttributeRepository.findById(fieldAttribute.getId()).get();
        // Disconnect from session so that the updates on updatedFieldAttribute are not directly saved in db
        em.detach(updatedFieldAttribute);
        updatedFieldAttribute
            .attributeName(UPDATED_ATTRIBUTE_NAME)
            .attributeValue(UPDATED_ATTRIBUTE_VALUE);
        FieldAttributeDTO fieldAttributeDTO = fieldAttributeMapper.toDto(updatedFieldAttribute);

        restFieldAttributeMockMvc.perform(put("/api/field-attributes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldAttributeDTO)))
            .andExpect(status().isOk());

        // Validate the FieldAttribute in the database
        List<FieldAttribute> fieldAttributeList = fieldAttributeRepository.findAll();
        assertThat(fieldAttributeList).hasSize(databaseSizeBeforeUpdate);
        FieldAttribute testFieldAttribute = fieldAttributeList.get(fieldAttributeList.size() - 1);
        assertThat(testFieldAttribute.getAttributeName()).isEqualTo(UPDATED_ATTRIBUTE_NAME);
        assertThat(testFieldAttribute.getAttributeValue()).isEqualTo(UPDATED_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingFieldAttribute() throws Exception {
        int databaseSizeBeforeUpdate = fieldAttributeRepository.findAll().size();

        // Create the FieldAttribute
        FieldAttributeDTO fieldAttributeDTO = fieldAttributeMapper.toDto(fieldAttribute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFieldAttributeMockMvc.perform(put("/api/field-attributes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fieldAttributeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FieldAttribute in the database
        List<FieldAttribute> fieldAttributeList = fieldAttributeRepository.findAll();
        assertThat(fieldAttributeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFieldAttribute() throws Exception {
        // Initialize the database
        fieldAttributeRepository.saveAndFlush(fieldAttribute);

        int databaseSizeBeforeDelete = fieldAttributeRepository.findAll().size();

        // Delete the fieldAttribute
        restFieldAttributeMockMvc.perform(delete("/api/field-attributes/{id}", fieldAttribute.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FieldAttribute> fieldAttributeList = fieldAttributeRepository.findAll();
        assertThat(fieldAttributeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
