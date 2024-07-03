package com.connector.api.service.profile.response;

import java.time.LocalDate;

import com.connector.api.domain.profile.Education;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileEducationResponse {

	private Long id;

	private String schoolName;

	private Integer yearsInSchool;

	private String major;

	private LocalDate startDate;

	private LocalDate endDate;

	public static ProfileEducationResponse of(final Education education) {
		return ProfileEducationResponse.builder()
			.id(education.getId())
			.schoolName(education.getSchoolName())
			.yearsInSchool(education.getYearsInSchool())
			.major(education.getMajor())
			.startDate(education.getStartDate())
			.endDate(education.getEndDate())
			.build();
	}
}
