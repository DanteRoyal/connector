package com.connector.api.domain.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserTokenRequest {

	@NotBlank
	private String email;

	@NotBlank
	private String password;
}
