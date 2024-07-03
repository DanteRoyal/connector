package com.connector.api.controller.profile;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connector.api.controller.profile.request.ProfileRequest;
import com.connector.api.global.auth.CurrentUser;
import com.connector.api.service.profile.ProfileService;
import com.connector.api.service.profile.response.MyProfileResponse;
import com.connector.api.service.profile.response.ProfileCreateResponse;
import com.connector.api.service.profile.response.ProfileDetailResponse;
import com.connector.api.service.profile.response.ProfileListResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ProfileController {

	private final ProfileService profileService;

	@GetMapping
	public List<ProfileListResponse> getProfiles() {
		return profileService.getProfiles();
	}

	@GetMapping("/{profileId}")
	public ProfileDetailResponse getProfileDetail(@PathVariable final Long profileId) {
		return profileService.getProfileDetail(profileId);
	}

	@PostMapping
	public ProfileCreateResponse createProfile(@CurrentUser final Long userId,
		@RequestPart(name = "request") @Valid final ProfileRequest reqeust,
		@RequestPart(name = "image", required = false) final MultipartFile image) {
		log.info("userId = {}", userId);
		return profileService.createProfile(userId, reqeust, image);
	}

	@GetMapping("/myProfile")
	public MyProfileResponse getMyProfile(@CurrentUser final Long userId) {
		return profileService.getMyProfile(userId);
	}

	@PutMapping
	public void updateProfile(@CurrentUser final Long userId, @RequestBody final ProfileRequest request) {
		profileService.updateProfile(userId, request);
	}

}