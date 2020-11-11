package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.QueryPlateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.QueryPlate}.
 */
public interface QueryPlateService {

    /**
     * Save a queryPlate.
     *
     * @param queryPlateDTO the entity to save.
     * @return the persisted entity.
     */
    QueryPlateDTO save(QueryPlateDTO queryPlateDTO);

    /**
     * Get all the queryPlates.
     *
     * @return the list of entities.
     */
    List<QueryPlateDTO> findAll();


    /**
     * Get the "id" queryPlate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QueryPlateDTO> findOne(Long id);

    /**
     * Delete the "id" queryPlate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
