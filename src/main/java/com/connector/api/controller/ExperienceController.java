package com.connector.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connector.api.domain.profile.request.ExperienceRequest;
import com.connector.api.domain.profile.response.ExperienceResponse;
import com.connector.api.global.auth.CurrentUser;
import com.connector.api.service.ExperienceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/experiences")
@RequiredArgsConstructor
public class ExperienceController {

	private final ExperienceService experienceService;

	@PostMapping
	public void addExperience(@CurrentUser final Long userId, @RequestBody @Valid final ExperienceRequest request) {
		experienceService.addExperience(userId, request);
	}

	@GetMapping("/{experienceId}")
	public ExperienceResponse getExperience(@PathVariable Long experienceId, @CurrentUser final Long userId) {
		return experienceService.getExperience(experienceId, userId);
	}

	@PutMapping("/{experienceId}")
	public void updateExperience(@PathVariable Long experienceId, @CurrentUser final Long userId,
		@RequestBody @Valid final ExperienceRequest request) {

		experienceService.updateExperience(experienceId, userId, request);

	}

	@DeleteMapping("/{experienceId}")
	public void deleteExperience(@PathVariable Long experienceId, @CurrentUser final Long userId) {
		experienceService.deleteExperience(experienceId, userId);
	}
}
