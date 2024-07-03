package com.connector.api.service.profile.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.connector.api.domain.profile.Profile;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyProfileResponse {

	private String nickname;

	private List<ProfileEducationResponse> educationResponses = new ArrayList<>();

	private List<ProfileExperienceResponse> experienceResponses = new ArrayList<>();

	public static MyProfileResponse of(final Profile profile) {
		return MyProfileResponse.builder()
			.nickname(profile.getNickname())
			.educationResponses(profile.getEducations().stream()
				.map(ProfileEducationResponse::of)
				.collect(Collectors.toList()))
			.experienceResponses(profile.getExperiences().stream()
				.map(ProfileExperienceResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
