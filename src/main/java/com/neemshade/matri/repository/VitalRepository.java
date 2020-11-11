package com.neemshade.matri.repository;

import com.neemshade.matri.domain.Vital;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Vital entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VitalRepository extends JpaRepository<Vital, Long> {
}
