package com.connector.api.domain.profile.response;

import com.connector.api.domain.profile.ProfessionalStatus;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.ProfileImage;

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
	private String nickname;

	@NotBlank
	private String website;

	@NotBlank
	private String techStacks;

	@NotBlank
	private String introduction;

	@NotBlank
	private String profileImageUrl;

	public static ProfileCreateResponse of(final Profile profile, final ProfileImage profileImage) {
		return ProfileCreateResponse.builder()
			.professionalStatus(profile.getProfessionalStatus())
			.nickname(profile.getNickname())
			.company(profile.getCompany())
			.website(profile.getWebsite())
			.techStacks(profile.getTechStacks())
			.introduction(profile.getIntroduction())
			.profileImageUrl(profileImage.getProfileImageUrl())
			.build();
	}
}
