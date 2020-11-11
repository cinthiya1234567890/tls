package com.neemshade.matri.service;

import com.neemshade.matri.domain.CascaderParam;
import com.neemshade.matri.service.dto.CascaderParamDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.neemshade.matri.domain.CascaderParam}.
 */
public interface CascaderParamService {

    /**
     * Save a cascaderParam.
     *
     * @param cascaderParamDTO the entity to save.
     * @return the persisted entity.
     */
    CascaderParamDTO save(CascaderParamDTO cascaderParamDTO);

    /**
     * Get all the cascaderParams.
     *
     * @return the list of entities.
     */
    List<CascaderParamDTO> findAll();


    /**
     * Get the "id" cascaderParam.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CascaderParamDTO> findOne(Long id);

    /**
     * Delete the "id" cascaderParam.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     *  get the record that has max peckOrder
     *  set of records under given parentId are considered
     * @param long1 
     */
    Optional<CascaderParamDTO> findMaxPeckOrderUnderParentId(Long cascaderId, Long parentId);
    
    /**
     * find unique paramTitle under given cascaderId and parentId
     * @param cascaderId
     * @param parentId
     * @param paramTitle
     * @return
     */
    Optional<CascaderParamDTO> findUniqueParamTitle(Long cascaderId, Long parentId, String paramTitle);
    
    /**
     * get the cascaderParam objects of the given cascaderName
     * the result is placed in a map.  with paramTitle as key and cascaderParam as value
     * @param cascaderName
     * @return
     */
    Map<String, CascaderParam> findAllParamsOfCascader(String cascaderName);
    
    /**
     * get the children of the given parent
     * @param parentId
     * @return
     */
    Map<String, CascaderParamDTO> findAllParamsOfParent(Long parentId);
    
}
