package com.neemshade.matri.repository;

import com.neemshade.matri.domain.ProfileParam;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProfileParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileParamRepository extends JpaRepository<ProfileParam, Long> {
}
