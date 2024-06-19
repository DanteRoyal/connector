package com.connector.api.domain.profile.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class MyProfileResponse {

	List<ProfileEducationResponse> educationResponseList = new ArrayList<>();

	List<ProfileExperienceResponse> experienceResponseList = new ArrayList<>();

	public MyProfileResponse(final List<ProfileEducationResponse> educationResponseList,
		final List<ProfileExperienceResponse> experienceResponseList) {
		this.educationResponseList = educationResponseList;
		this.experienceResponseList = experienceResponseList;
	}
}
