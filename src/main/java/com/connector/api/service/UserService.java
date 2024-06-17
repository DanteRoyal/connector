package com.connector.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connector.api.domain.UserRepository;
import com.connector.api.domain.user.User;
import com.connector.api.domain.user.request.UserSignRequest;
import com.connector.api.global.exception.RestApiException;
import com.connector.api.global.exception.UserErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public Long registerUser(final UserSignRequest request) {

		final String email = request.getEmail();

		if (userRepository.existsByEmail(email)) {
			throw new RestApiException(UserErrorCode.EMAIL_ALREADY_EXIST);
		}

		final User newUser = request.toEntity();

		userRepository.save(newUser);

		return newUser.getId();
	}

	public void unregisterUser(final Long userId) {
		userRepository.deleteById(userId);
	}

}
