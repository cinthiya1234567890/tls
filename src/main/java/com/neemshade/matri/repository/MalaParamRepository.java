package com.neemshade.matri.repository;

import com.neemshade.matri.domain.MalaParam;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MalaParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MalaParamRepository extends JpaRepository<MalaParam, Long> {

	Optional<MalaParam> findTopByMalaIdOrderByPeckOrderDesc(Long malaId);
}
