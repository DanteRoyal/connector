package com.connector.api.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {

	PROFILE_ALREADY_EXIST(HttpStatus.CONFLICT, "해당 유저의 프로필이 이미 존재합니다."),
	PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로필이 존재하지 않습니다"),
	;

	private final HttpStatus httpStatus;

	private final String message;

}
