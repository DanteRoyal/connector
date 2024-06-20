package com.connector.api.domain.profile.response;

import java.time.LocalDate;

import com.connector.api.domain.profile.Experience;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileExperienceResponse {

	private Long id;

	private String company;

	private String position;

	private LocalDate startDate;

	private LocalDate endDate;

	public static ProfileExperienceResponse of(final Experience experience) {
		return ProfileExperienceResponse.builder()
			.id(experience.getId())
			.company(experience.getCompany())
			.position(experience.getPosition())
			.startDate(experience.getStartDate())
			.endDate(experience.getEndDate())
			.build();
	}

}
