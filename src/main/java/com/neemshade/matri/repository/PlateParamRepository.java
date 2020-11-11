package com.neemshade.matri.repository;

import com.neemshade.matri.domain.PlateParam;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PlateParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlateParamRepository extends JpaRepository<PlateParam, Long> {
}
