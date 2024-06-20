package com.connector.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.connector.api.domain.ProfileImageRepository;
import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.profile.Profile;
import com.connector.api.global.exception.ProfileErrorCode;
import com.connector.api.global.exception.RestApiException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileImageService {

	private final ProfileImageRepository profileImageRepository;
	private final ProfileRepository profileRepository;

	public void addProfileImage(final Long userId, final MultipartFile profileImage) {
		Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

	}
}
