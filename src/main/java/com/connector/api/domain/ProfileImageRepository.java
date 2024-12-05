package com.connector.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connector.api.domain.profile.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
