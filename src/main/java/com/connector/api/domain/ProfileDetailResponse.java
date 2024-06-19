package com.connector.api.domain;

import java.util.List;

import com.connector.api.domain.profile.Education;
import com.connector.api.domain.profile.Experience;
import com.connector.api.domain.profile.Profile;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileDetailResponse {

	private String nickname;

	private String profileImageUrl;

	private String introduction;

	private String techStacks;

	private List<Education> educations;

	private List<Experience> experiences;

	private String website;

	public static ProfileDetailResponse of(final Profile profile) {
		return ProfileDetailResponse.builder()
			.nickname(profile.getNickname())
			// .profileImageUrl(profile.getProfileImage().getProfileImageUrl())
			.introduction(profile.getIntroduction())
			.techStacks(profile.getTechStacks())
			.educations(profile.getEducations())
			.experiences(profile.getExperiences())
			.website(profile.getWebsite())
			.build();
	}

}
