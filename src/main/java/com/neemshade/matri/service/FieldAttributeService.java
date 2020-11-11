package com.neemshade.matri.service;

import com.neemshade.matri.service.dto.FieldAttributeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.FieldAttribute}.
 */
public interface FieldAttributeService {

    /**
     * Save a fieldAttribute.
     *
     * @param fieldAttributeDTO the entity to save.
     * @return the persisted entity.
     */
    FieldAttributeDTO save(FieldAttributeDTO fieldAttributeDTO);

    /**
     * Get all the fieldAttributes.
     *
     * @return the list of entities.
     */
    List<FieldAttributeDTO> findAll();


    /**
     * Get the "id" fieldAttribute.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FieldAttributeDTO> findOne(Long id);

    /**
     * Delete the "id" fieldAttribute.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     * delete all fieldAttributes under field
     * @param fieldId
     */
    void deleteUnderField(Long fieldId);
    
    /**
     * returns unique record of given field and attribute name
     * @param fieldId
     * @param attributeName
     * @return
     */
    Optional<FieldAttributeDTO> findUniqueFieldAttribute(Long fieldId, String attributeName);
}
