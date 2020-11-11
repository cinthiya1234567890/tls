package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.VitalDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.Vital}.
 */
public interface VitalService {

    /**
     * Save a vital.
     *
     * @param vitalDTO the entity to save.
     * @return the persisted entity.
     */
    VitalDTO save(VitalDTO vitalDTO);

    /**
     * Get all the vitals.
     *
     * @return the list of entities.
     */
    List<VitalDTO> findAll();


    /**
     * Get the "id" vital.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VitalDTO> findOne(Long id);

    /**
     * Delete the "id" vital.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
