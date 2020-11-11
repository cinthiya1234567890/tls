package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.MalaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.Mala}.
 */
public interface MalaService {

    /**
     * Save a mala.
     *
     * @param malaDTO the entity to save.
     * @return the persisted entity.
     */
    MalaDTO save(MalaDTO malaDTO);

    /**
     * Get all the malas.
     *
     * @return the list of entities.
     */
    List<MalaDTO> findAll();


    /**
     * Get the "id" mala.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MalaDTO> findOne(Long id);

    /**
     * Delete the "id" mala.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     * find unique mala with the given mala name
     * @param malaName
     * @return
     */
    Optional<MalaDTO> findUniqueMala(String malaName);
}
