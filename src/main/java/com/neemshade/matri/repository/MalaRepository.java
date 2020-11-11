package com.neemshade.matri.repository;

import com.neemshade.matri.domain.Mala;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Mala entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MalaRepository extends JpaRepository<Mala, Long> {
	Optional<Mala> findTopByMalaName(String malaName);
}
