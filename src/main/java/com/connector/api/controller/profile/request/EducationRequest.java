package com.connector.api.controller.profile.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.connector.api.domain.profile.Education;
import com.connector.api.domain.profile.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EducationRequest {

	@NotBlank
	private String schoolName;

	@NotNull
	private Integer yearsInSchool;

	@NotBlank
	private String major;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate startDate;
	/*
	 *
	 * TODO: Now는 어떻게 처리할지 고민하기
	 * */

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate endDate;

	public Education toEntity(final Profile profile) {
		return Education.builder()
			.schoolName(this.schoolName)
			.yearsInSchool(this.yearsInSchool)
			.major(this.major)
			.startDate(this.startDate)
			.endDate(this.endDate)
			.profile(profile)
			.build();
	}

}
