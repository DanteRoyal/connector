package com.connector.api.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connector.api.domain.profile.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
	Optional<Experience> findByIdAndProfileId(Long experienceId, Long profileId);
}
