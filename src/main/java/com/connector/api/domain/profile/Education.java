package com.connector.api.domain.profile;

import java.time.LocalDate;

import com.connector.api.controller.profile.request.EducationRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

	public void updateEducation(final EducationRequest request) {
		this.schoolName = request.getSchoolName();
		this.yearsInSchool = request.getYearsInSchool();
		this.major = request.getMajor();
		this.startDate = request.getStartDate();
		this.endDate = request.getEndDate();
	}

}
