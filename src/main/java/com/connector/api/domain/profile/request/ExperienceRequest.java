package com.connector.api.domain.profile.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.connector.api.domain.profile.Experience;
import com.connector.api.domain.profile.Profile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExperienceRequest {

	@NotBlank
	private String company;

	@NotBlank
	private String position;

	@NotBlank
	private String jobDescription;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate endDate;

	public Experience toEntity(final Profile profile) {
		return Experience.builder()
			.company(this.company)
			.position(this.position)
			.jobDescription(this.jobDescription)
			.startDate(this.startDate)
			.endDate(this.endDate)
			.profile(profile)
			.build();
	}
}
