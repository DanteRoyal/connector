package com.connector.api.domain;

import com.connector.api.domain.profile.Profile;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileListResponse {

	private Long profileId;

	private String nickname;

	// private String profileImageUrl;

	private String introduction;

	private String techStacks;

	public static ProfileListResponse of(final Profile profile) {
		return ProfileListResponse.builder()
			.profileId(profile.getId())
			.nickname(profile.getNickname())
			// .profileImageUrl(profile.getProfileImage().getProfileImageUrl())
			.introduction(profile.getIntroduction())
			.techStacks(profile.getTechStacks())
			.build();
	}
}
