package com.neemshade.matri.repository;

import com.neemshade.matri.domain.Query;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Query entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
}
