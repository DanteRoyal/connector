package com.connector.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connector.api.domain.EducationRepository;
import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.profile.Education;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.request.EducationRequest;
import com.connector.api.domain.profile.response.EducationResponse;
import com.connector.api.global.exception.ProfileErrorCode;
import com.connector.api.global.exception.RestApiException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationService {

	private final EducationRepository educationRepository;

	private final ProfileRepository profileRepository;

	public void addEducation(final Long userId, final EducationRequest request) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final Education education = request.toEntity(foundProfile);

		educationRepository.save(education);
	}

	public void updateEducation(final Long educationId, final Long userId, final EducationRequest request) {
		final Education foundEducation = educationRepository.findById(educationId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EDUCATION_NOT_FOUND));

		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		if (!foundEducation.getProfile().getId().equals(foundProfile.getId())) {
			throw new RestApiException(ProfileErrorCode.UNAUTHORIZED_EDUCATION_REQUEST);
		}

		foundEducation.updateEducation(request);

	}

	public void deleteEducation(final Long educationId, final Long userId) {
		/*
		 *
		 * TODO: 검증방식에 대해서 고민해보기
		 * */
		final Education foundEducation = educationRepository.findById(educationId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EDUCATION_NOT_FOUND));

		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		if (!foundEducation.getProfile().getId().equals(foundProfile.getId())) {
			throw new RestApiException(ProfileErrorCode.UNAUTHORIZED_EDUCATION_REQUEST);
		}

		educationRepository.delete(foundEducation);
	}

	@Transactional(readOnly = true)
	public EducationResponse getEducation(final Long educationId, final Long userId) {
		final Education foundEducation = educationRepository.findById(educationId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.EDUCATION_NOT_FOUND));

		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		if (!foundEducation.getProfile().getId().equals(foundProfile.getId())) {
			throw new RestApiException(ProfileErrorCode.UNAUTHORIZED_EDUCATION_REQUEST);
		}
		return EducationResponse.of(foundEducation);
	}
}
