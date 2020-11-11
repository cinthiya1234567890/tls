package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.PlateParamDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.PlateParam}.
 */
public interface PlateParamService {

    /**
     * Save a plateParam.
     *
     * @param plateParamDTO the entity to save.
     * @return the persisted entity.
     */
    PlateParamDTO save(PlateParamDTO plateParamDTO);

    /**
     * Get all the plateParams.
     *
     * @return the list of entities.
     */
    List<PlateParamDTO> findAll();


    /**
     * Get the "id" plateParam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlateParamDTO> findOne(Long id);

    /**
     * Delete the "id" plateParam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
