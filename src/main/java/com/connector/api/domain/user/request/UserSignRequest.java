package com.connector.api.domain.user.request;

import com.connector.api.domain.user.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSignRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.build();
	}

}
