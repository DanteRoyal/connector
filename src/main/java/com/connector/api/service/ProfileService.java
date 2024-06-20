package com.connector.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.connector.api.domain.ProfileImageRepository;
import com.connector.api.domain.ProfileRepository;
import com.connector.api.domain.UserRepository;
import com.connector.api.domain.profile.Profile;
import com.connector.api.domain.profile.ProfileImage;
import com.connector.api.domain.profile.request.ProfileRequest;
import com.connector.api.domain.profile.response.MyProfileResponse;
import com.connector.api.domain.profile.response.ProfileCreateResponse;
import com.connector.api.domain.profile.response.ProfileDetailResponse;
import com.connector.api.domain.profile.response.ProfileEducationResponse;
import com.connector.api.domain.profile.response.ProfileExperienceResponse;
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

	private final ProfileImageRepository profileImageRepository;

	@Value("${image.url}")
	private String IMAGE_BASE_URL;

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

	public ProfileCreateResponse createProfile(final Long userId, final ProfileRequest reqeust,
		final MultipartFile image) {
		if (profileRepository.existsByUserId(userId)) {
			throw new RestApiException(ProfileErrorCode.PROFILE_ALREADY_EXIST);
		}

		/*
		 * TODO 리팩토링 어떻게? 고민
		 *
		 * */

		final ProfileImage profileImage = checkIfImageExist(image);

		final User foundUser = userRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(UserErrorCode.USER_NOT_FOUND));

		final Profile newProfile = reqeust.toEntity(foundUser, profileImage);

		profileRepository.save(newProfile);
		profileImageRepository.save(profileImage);

		return ProfileCreateResponse.of(newProfile, profileImage);
	}

	private ProfileImage checkIfImageExist(MultipartFile image) {
		if (image == null) {
			return ProfileImage.builder()
				.profileImageUrl(IMAGE_BASE_URL + "defaultImage")
				.build();
		}

		String originalFileName = image.getOriginalFilename();
		String storedFileName = UUID.randomUUID() + "-" + originalFileName;

		try {
			image.transferTo(new File(IMAGE_BASE_URL + storedFileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return ProfileImage.builder()
			.profileImageUrl(IMAGE_BASE_URL + storedFileName)
			.originalFileName(originalFileName)
			.storedFileName(storedFileName)
			.fileSize(image.getSize())
			.build();
	}

	@Transactional(readOnly = true)
	public MyProfileResponse viewMyProfile(final Long userId) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		final List<ProfileEducationResponse> educations = foundProfile.getEducations().stream()
			.map(ProfileEducationResponse::of)
			.collect(Collectors.toList());
		final List<ProfileExperienceResponse> experiences = foundProfile.getExperiences().stream()
			.map(ProfileExperienceResponse::of)
			.collect(Collectors.toList());

		return new MyProfileResponse(educations, experiences);

	}

	public void updateProfile(final Long userId, final ProfileRequest request) {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ProfileErrorCode.PROFILE_NOT_FOUND));

		foundProfile.updateProfile(request);
	}

}
