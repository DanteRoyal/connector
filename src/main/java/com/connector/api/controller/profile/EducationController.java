package com.connector.api.controller.profile;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connector.api.controller.profile.request.EducationRequest;
import com.connector.api.global.auth.CurrentUser;
import com.connector.api.service.profile.EducationService;
import com.connector.api.service.profile.response.EducationResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/educations")
public class EducationController {

	private final EducationService educationService;

	@PostMapping
	public void addEducation(@CurrentUser final Long userId, @RequestBody @Valid final EducationRequest request) {
		educationService.addEducation(userId, request);
	}

	@GetMapping("/{educationId}")
	public EducationResponse getEducation(@PathVariable Long educationId, @CurrentUser final Long userId) {
		return educationService.getEducation(educationId, userId);
	}

	@PutMapping("/{educationId}")
	public void updateEducation(@PathVariable Long educationId, @CurrentUser final Long userId,
		@RequestBody @Valid final EducationRequest request) {

		educationService.updateEducation(educationId, userId, request);
	}

	@DeleteMapping("/{educationId}")
	public void deleteEducation(@PathVariable Long educationId, @CurrentUser final Long userId) {
		educationService.deleteEducation(educationId, userId);
	}
}
