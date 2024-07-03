package com.connector.api.service.profile.response;

import java.time.LocalDate;

import com.connector.api.domain.profile.Experience;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExperienceResponse {

	private Long id;

	private String company;

	private String position;

	private String jobDescription;

	private LocalDate startDate;

	private LocalDate endDate;

	public static ExperienceResponse of(final Experience experience) {
		return ExperienceResponse.builder()
			.id(experience.getId())
			.company(experience.getCompany())
			.position(experience.getPosition())
			.jobDescription(experience.getJobDescription())
			.startDate(experience.getStartDate())
			.endDate(experience.getEndDate())
			.build();
	}
}
