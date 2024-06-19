package com.connector.api.domain.profile.request;

import com.connector.api.domain.profile.ProfessionalStatus;
import com.connector.api.domain.profile.Profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileCreateRequest {

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

	/*
	 * TODO Tech 스택 프로필정보 받아와서 넣어주기
	 *
	 * */
	public Profile toEntity() {
		return Profile.builder()
			.professionalStatus(this.professionalStatus)
			.company(this.company)
			.website(this.website)
			.techStacks(this.techStacks)
			.introduction(this.introduction)
			.build();
	}

}
