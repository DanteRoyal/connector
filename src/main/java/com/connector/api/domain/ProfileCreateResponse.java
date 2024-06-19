package com.connector.api.domain;

import com.connector.api.domain.profile.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileCreateResponse {

	@NotNull
	private ProfessionalStatus professionalStatus;

	@NotBlank
	private String company;

	@NotBlank
	private String website;

	@NotBlank
	private String techStacks;

	@NotBlank
	private String introduction;

	static ProfileCreateResponse of(final Profile profile) {
		return ProfileCreateResponse.builder()
			.professionalStatus(profile.getProfessionalStatus())
			.company(profile.getCompany())
			.website(profile.getWebsite())
			.techStacks(profile.getTechStacks())
			.introduction(profile.getIntroduction())
			.build();
	}
}
