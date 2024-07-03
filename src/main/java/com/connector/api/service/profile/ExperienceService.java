package com.connector.api.service.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connector.api.controller.profile.request.ExperienceRequest;
import com.connector.api.domain.ExperienceRepository;
import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.profile.Experience;
import com.connector.api.domain.profile.Profile;
import com.connector.api.global.exception.ProfileErrorCode;
import com.connector.api.global.exception.RestApiException;
import com.connector.api.service.profile.response.ExperienceResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExperienceService {

	private final ExperienceRepository experienceRepository;

	private final ProfileRepository profileRepository;

	public void addExperience(final Long userId, final ExperienceRequest request) {
		/*
		 *  TODO : 연관관계 편의 메서드 작성하기
		 * */
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final Experience newExperience = request.toEntity(foundProfile);

		experienceRepository.save(newExperience);
	}

	@Transactional(readOnly = true)
	public ExperienceResponse getExperience(final Long experienceId, final Long userId) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final Experience experience = experienceRepository.findByIdAndProfileId(experienceId, foundProfile.getId())
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EXPERIENCE_NOT_FOUND));

		return ExperienceResponse.of(experience);
	}

	public void updateExperience(Long experienceId, Long userId, ExperienceRequest request) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final Experience foundExperience = experienceRepository.findByIdAndProfileId(experienceId, foundProfile.getId())
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EXPERIENCE_NOT_FOUND));

		foundExperience.updateExperience(request);
	}

	public void deleteExperience(final Long experienceId, final Long userId) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final Experience foundExperience = experienceRepository.findByIdAndProfileId(experienceId, foundProfile.getId())
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EXPERIENCE_NOT_FOUND));

		experienceRepository.delete(foundExperience);

	}
}