package com.connector.api.controller.profile.request;

import com.connector.api.domain.profile.ProfessionalStatus;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.ProfileImage;
import com.connector.api.domain.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileRequest {

	@NotNull
	private ProfessionalStatus professionalStatus;

	@NotBlank
	private String company;

	@NotBlank
	private String nickname;

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
	public Profile toEntity(final User user, final ProfileImage profileImage) {
		return Profile.builder()
			.professionalStatus(this.professionalStatus)
			.company(this.company)
			.nickname(this.nickname)
			.website(this.website)
			.techStacks(this.techStacks)
			.introduction(this.introduction)
			.profileImage(profileImage)
			.user(user)
			.build();
	}

}
