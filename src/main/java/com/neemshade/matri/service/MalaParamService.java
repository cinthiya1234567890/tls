package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.MalaParamDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.MalaParam}.
 */
public interface MalaParamService {

    /**
     * Save a malaParam.
     *
     * @param malaParamDTO the entity to save.
     * @return the persisted entity.
     */
    MalaParamDTO save(MalaParamDTO malaParamDTO);

    /**
     * Get all the malaParams.
     *
     * @return the list of entities.
     */
    List<MalaParamDTO> findAll();


    /**
     * Get the "id" malaParam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MalaParamDTO> findOne(Long id);

    /**
     * Delete the "id" malaParam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     * get the record that has max peckOrder for a given mala
     * @param malaId
     * @return
     */
    Optional<MalaParamDTO> findMaxPeckOrderUnderMala(Long malaId);
}
