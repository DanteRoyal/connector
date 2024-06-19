package com.connector.api.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestApiException.class)
	public ResponseEntity<?> handleCustomException(final RestApiException e) {

		ErrorCode errorCode = e.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(errorCode.getMessage());

	}
}
