package com.connector.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connector.api.domain.profile.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
