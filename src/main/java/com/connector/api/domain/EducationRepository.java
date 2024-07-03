package com.connector.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connector.api.domain.profile.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
