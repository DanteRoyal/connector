package com.connector.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.UserRepository;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.request.ProfileCreateRequest;
import com.connector.api.domain.profile.response.ProfileCreateResponse;
import com.connector.api.domain.profile.response.ProfileDetailResponse;
import com.connector.api.domain.profile.response.ProfileListResponse;
import com.connector.api.domain.user.User;
import com.connector.api.global.exception.ProfileErrorCode;
import com.connector.api.global.exception.RestApiException;
import com.connector.api.global.exception.UserErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

	private final ProfileRepository profileRepository;

	private final UserRepository userRepository;

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

	public ProfileCreateResponse createProfile(final Long userId, final ProfileCreateRequest reqeust) {
		if (profileRepository.existsByUserId(userId)) {
			throw new RestApiException(ProfileErrorCode.PROFILE_ALREADY_EXIST);
		}

		final User foundUser = userRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(UserErrorCode.USER_NOT_FOUND));

		final Profile newProfile = reqeust.toEntity(foundUser);

		profileRepository.save(newProfile);

		return ProfileCreateResponse.of(newProfile);
	}
}
