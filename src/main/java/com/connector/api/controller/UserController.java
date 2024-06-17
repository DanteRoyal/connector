package com.connector.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connector.api.domain.user.request.UserSignRequest;
import com.connector.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public Long registerUser(@RequestBody @Valid final UserSignRequest request) {
		return userService.registerUser(request);
	}

	@DeleteMapping("/{userId}")
	public void unregisterUser(@PathVariable final Long userId) {
		userService.unregisterUser(userId);
	}
}
