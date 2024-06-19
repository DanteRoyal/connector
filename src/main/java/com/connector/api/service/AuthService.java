package com.connector.api.service;

import org.springframework.stereotype.Service;

import com.connector.api.domain.UserRepository;
import com.connector.api.domain.user.User;
import com.connector.api.domain.user.request.UserLoginRequest;
import com.connector.api.global.exception.RestApiException;
import com.connector.api.global.exception.UserErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final JwtProvider jwtProvider;

	private final UserRepository userRepository;

	public String login(final UserLoginRequest request) {
		final User foundUser = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new RestApiException(UserErrorCode.EMAIL_NOT_FOUND));

		if (!foundUser.getPassword().equals(request.getPassword())) {
			throw new RestApiException(UserErrorCode.INCORRECT_PASSWORD);
		}

		return jwtProvider.createToken(String.valueOf(foundUser.getId()));
	}
}
