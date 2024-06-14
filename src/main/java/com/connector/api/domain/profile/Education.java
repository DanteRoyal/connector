package com.connector.api.domain.profile;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String schoolName;

	private Integer yearsInSchool;

	private String major;

	private LocalDate startDate;

	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

}
