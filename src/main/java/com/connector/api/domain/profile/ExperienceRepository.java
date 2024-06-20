package com.connector.api.domain.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
	Optional<Experience> findByIdAndProfileId(Long experienceId, Long profileId);
}
