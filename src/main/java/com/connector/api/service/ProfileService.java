package com.connector.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.request.ProfileCreateRequest;
import com.connector.api.domain.profile.response.ProfileCreateResponse;
import com.connector.api.domain.profile.response.ProfileDetailResponse;
import com.connector.api.domain.profile.response.ProfileListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

	private final ProfileRepository profileRepository;

	@Transactional(readOnly = true)
	public List<ProfileListResponse> getProfiles() {
		List<Profile> profiles = profileRepository.findAll();

		return profiles.stream().map(ProfileListResponse::of).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProfileDetailResponse getProfileDetail(final Long profileId) {
		/*
		 * TODO 예외처리
		 * */
		Profile foundProfile = profileRepository.findById(profileId)
			.orElseThrow(() -> new RuntimeException("프로필 못찾음"));

		return ProfileDetailResponse.of(foundProfile);
	}

	public ProfileCreateResponse createProfile(final ProfileCreateRequest reqeust) {
		final Profile newProfile = reqeust.toEntity();

		profileRepository.save(newProfile);

		return ProfileCreateResponse.of(newProfile);
	}
}
