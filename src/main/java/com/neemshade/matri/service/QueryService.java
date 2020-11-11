package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.QueryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.Query}.
 */
public interface QueryService {

    /**
     * Save a query.
     *
     * @param queryDTO the entity to save.
     * @return the persisted entity.
     */
    QueryDTO save(QueryDTO queryDTO);

    /**
     * Get all the queries.
     *
     * @return the list of entities.
     */
    List<QueryDTO> findAll();


    /**
     * Get the "id" query.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QueryDTO> findOne(Long id);

    /**
     * Delete the "id" query.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
