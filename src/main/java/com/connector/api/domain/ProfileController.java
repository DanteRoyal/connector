package com.connector.api.domain;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
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
	public ProfileCreateResponse createProfile(@RequestBody @Valid final ProfileCreateRequest reqeust) {
		return profileService.createProfile(reqeust);
	}

	@PatchMapping
	public void updateProfile() {

	}

	@PostMapping("/experiences")
	public void addExperience() {

	}

	@PostMapping("/educations")
	public void addEducation() {

	}

}
