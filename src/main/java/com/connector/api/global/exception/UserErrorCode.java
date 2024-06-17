package com.connector.api.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

	EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "해당 이메일이 이미 존재함."),
	EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이메일로 가입된 정보가 없습니다."),
	INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 맞지 않습니다.");

	private final HttpStatus httpStatus;

	private final String message;
}
