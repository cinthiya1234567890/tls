package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.IssueDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.Issue}.
 */
public interface IssueService {

    /**
     * Save a issue.
     *
     * @param issueDTO the entity to save.
     * @return the persisted entity.
     */
    IssueDTO save(IssueDTO issueDTO);

    /**
     * Get all the issues.
     *
     * @return the list of entities.
     */
    List<IssueDTO> findAll();


    /**
     * Get the "id" issue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IssueDTO> findOne(Long id);

    /**
     * Delete the "id" issue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
