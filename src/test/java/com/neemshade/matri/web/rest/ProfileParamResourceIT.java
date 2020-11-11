package com.neemshade.matri.web.rest;

import com.neemshade.matri.MatriSearchApp;
import com.neemshade.matri.domain.ProfileParam;
import com.neemshade.matri.repository.ProfileParamRepository;
import com.neemshade.matri.service.ProfileParamService;
import com.neemshade.matri.service.dto.ProfileParamDTO;
import com.neemshade.matri.service.mapper.ProfileParamMapper;

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
 * Integration tests for the {@link ProfileParamResource} REST controller.
 */
@SpringBootTest(classes = MatriSearchApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfileParamResourceIT {

    private static final String DEFAULT_DATA_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_VALUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DATA_INT = 1;
    private static final Integer UPDATED_DATA_INT = 2;

    private static final String DEFAULT_USER_ENTERED_CUSTOM_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_USER_ENTERED_CUSTOM_VALUE = "BBBBBBBBBB";

    @Autowired
    private ProfileParamRepository profileParamRepository;

    @Autowired
    private ProfileParamMapper profileParamMapper;

    @Autowired
    private ProfileParamService profileParamService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfileParamMockMvc;

    private ProfileParam profileParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileParam createEntity(EntityManager em) {
        ProfileParam profileParam = new ProfileParam()
            .dataValue(DEFAULT_DATA_VALUE)
            .dataInt(DEFAULT_DATA_INT)
            .userEnteredCustomValue(DEFAULT_USER_ENTERED_CUSTOM_VALUE);
        return profileParam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileParam createUpdatedEntity(EntityManager em) {
        ProfileParam profileParam = new ProfileParam()
            .dataValue(UPDATED_DATA_VALUE)
            .dataInt(UPDATED_DATA_INT)
            .userEnteredCustomValue(UPDATED_USER_ENTERED_CUSTOM_VALUE);
        return profileParam;
    }

    @BeforeEach
    public void initTest() {
        profileParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfileParam() throws Exception {
        int databaseSizeBeforeCreate = profileParamRepository.findAll().size();
        // Create the ProfileParam
        ProfileParamDTO profileParamDTO = profileParamMapper.toDto(profileParam);
        restProfileParamMockMvc.perform(post("/api/profile-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileParamDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfileParam in the database
        List<ProfileParam> profileParamList = profileParamRepository.findAll();
        assertThat(profileParamList).hasSize(databaseSizeBeforeCreate + 1);
        ProfileParam testProfileParam = profileParamList.get(profileParamList.size() - 1);
        assertThat(testProfileParam.getDataValue()).isEqualTo(DEFAULT_DATA_VALUE);
        assertThat(testProfileParam.getDataInt()).isEqualTo(DEFAULT_DATA_INT);
        assertThat(testProfileParam.getUserEnteredCustomValue()).isEqualTo(DEFAULT_USER_ENTERED_CUSTOM_VALUE);
    }

    @Test
    @Transactional
    public void createProfileParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileParamRepository.findAll().size();

        // Create the ProfileParam with an existing ID
        profileParam.setId(1L);
        ProfileParamDTO profileParamDTO = profileParamMapper.toDto(profileParam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileParamMockMvc.perform(post("/api/profile-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileParam in the database
        List<ProfileParam> profileParamList = profileParamRepository.findAll();
        assertThat(profileParamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfileParams() throws Exception {
        // Initialize the database
        profileParamRepository.saveAndFlush(profileParam);

        // Get all the profileParamList
        restProfileParamMockMvc.perform(get("/api/profile-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profileParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].dataValue").value(hasItem(DEFAULT_DATA_VALUE)))
            .andExpect(jsonPath("$.[*].dataInt").value(hasItem(DEFAULT_DATA_INT)))
            .andExpect(jsonPath("$.[*].userEnteredCustomValue").value(hasItem(DEFAULT_USER_ENTERED_CUSTOM_VALUE)));
    }
    
    @Test
    @Transactional
    public void getProfileParam() throws Exception {
        // Initialize the database
        profileParamRepository.saveAndFlush(profileParam);

        // Get the profileParam
        restProfileParamMockMvc.perform(get("/api/profile-params/{id}", profileParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profileParam.getId().intValue()))
            .andExpect(jsonPath("$.dataValue").value(DEFAULT_DATA_VALUE))
            .andExpect(jsonPath("$.dataInt").value(DEFAULT_DATA_INT))
            .andExpect(jsonPath("$.userEnteredCustomValue").value(DEFAULT_USER_ENTERED_CUSTOM_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingProfileParam() throws Exception {
        // Get the profileParam
        restProfileParamMockMvc.perform(get("/api/profile-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfileParam() throws Exception {
        // Initialize the database
        profileParamRepository.saveAndFlush(profileParam);

        int databaseSizeBeforeUpdate = profileParamRepository.findAll().size();

        // Update the profileParam
        ProfileParam updatedProfileParam = profileParamRepository.findById(profileParam.getId()).get();
        // Disconnect from session so that the updates on updatedProfileParam are not directly saved in db
        em.detach(updatedProfileParam);
        updatedProfileParam
            .dataValue(UPDATED_DATA_VALUE)
            .dataInt(UPDATED_DATA_INT)
            .userEnteredCustomValue(UPDATED_USER_ENTERED_CUSTOM_VALUE);
        ProfileParamDTO profileParamDTO = profileParamMapper.toDto(updatedProfileParam);

        restProfileParamMockMvc.perform(put("/api/profile-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileParamDTO)))
            .andExpect(status().isOk());

        // Validate the ProfileParam in the database
        List<ProfileParam> profileParamList = profileParamRepository.findAll();
        assertThat(profileParamList).hasSize(databaseSizeBeforeUpdate);
        ProfileParam testProfileParam = profileParamList.get(profileParamList.size() - 1);
        assertThat(testProfileParam.getDataValue()).isEqualTo(UPDATED_DATA_VALUE);
        assertThat(testProfileParam.getDataInt()).isEqualTo(UPDATED_DATA_INT);
        assertThat(testProfileParam.getUserEnteredCustomValue()).isEqualTo(UPDATED_USER_ENTERED_CUSTOM_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingProfileParam() throws Exception {
        int databaseSizeBeforeUpdate = profileParamRepository.findAll().size();

        // Create the ProfileParam
        ProfileParamDTO profileParamDTO = profileParamMapper.toDto(profileParam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileParamMockMvc.perform(put("/api/profile-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileParamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileParam in the database
        List<ProfileParam> profileParamList = profileParamRepository.findAll();
        assertThat(profileParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfileParam() throws Exception {
        // Initialize the database
        profileParamRepository.saveAndFlush(profileParam);

        int databaseSizeBeforeDelete = profileParamRepository.findAll().size();

        // Delete the profileParam
        restProfileParamMockMvc.perform(delete("/api/profile-params/{id}", profileParam.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfileParam> profileParamList = profileParamRepository.findAll();
        assertThat(profileParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
