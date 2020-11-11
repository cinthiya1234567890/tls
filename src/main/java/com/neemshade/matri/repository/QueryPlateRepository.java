package com.neemshade.matri.repository;

import com.neemshade.matri.domain.QueryPlate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QueryPlate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QueryPlateRepository extends JpaRepository<QueryPlate, Long> {
}
