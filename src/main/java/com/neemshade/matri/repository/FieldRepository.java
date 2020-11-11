package com.neemshade.matri.repository;

import com.neemshade.matri.domain.Field;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Field entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

	Optional<Field> findTopByFieldName(String fieldName);
	
	Optional<Field> findTopByMalaParamsMalaMalaNameAndFieldName(String malaName, String fieldName);
	
	Optional<Field> findTopByOrderByPeckOrderDesc();
}
