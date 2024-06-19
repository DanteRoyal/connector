package com.connector.api.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileErrorCode implements ErrorCode {

	PROFILE_ALREADY_EXIST(HttpStatus.CONFLICT, "해당 유저의 프로필이 이미 존재합니다."),
	PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로필이 존재하지 않습니다"),

	EDUCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 학력이 존재 하지 않습니다"),
	UNAUTHORIZED_EDUCATION_REQUEST(HttpStatus.UNAUTHORIZED, "해당 학력에 대한 권한이 없습니다.");

	private final HttpStatus httpStatus;

	private final String message;

}
