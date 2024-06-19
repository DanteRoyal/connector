package com.connector.api.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	HttpStatus getHttpStatus();

	String getMessage();
}
