package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.ProfileParamDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.ProfileParam}.
 */
public interface ProfileParamService {

    /**
     * Save a profileParam.
     *
     * @param profileParamDTO the entity to save.
     * @return the persisted entity.
     */
    ProfileParamDTO save(ProfileParamDTO profileParamDTO);

    /**
     * Get all the profileParams.
     *
     * @return the list of entities.
     */
    List<ProfileParamDTO> findAll();


    /**
     * Get the "id" profileParam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfileParamDTO> findOne(Long id);

    /**
     * Delete the "id" profileParam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
