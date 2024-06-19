package com.connector.api.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connector.api.domain.profile.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	boolean existsByUserId(Long userId);

	Optional<Profile> findByUserId(Long userId);
}
