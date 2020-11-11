package com.neemshade.matri.repository;

import com.neemshade.matri.domain.Cascader;
import com.neemshade.matri.domain.CascaderParam;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CascaderParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CascaderParamRepository extends JpaRepository<CascaderParam, Long> {

	Optional<CascaderParam> findTopByCascaderIdAndParentIdOrderByPeckOrderDesc(Long cascaderId, Long parentId);
	Optional<CascaderParam> findTopByCascaderIdAndParentIdAndParamTitleIgnoreCase(
			Long cascaderId, Long parentId, String paramTitle);
	
	// return all param of given cascaderName
	List<CascaderParam> findAllByCascaderCascaderNameOrderByPeckOrder(String cascaderName);
	
	// return all param of given parent
	List<CascaderParam> findAllByParentIdOrderByPeckOrder(Long parentId);
}
