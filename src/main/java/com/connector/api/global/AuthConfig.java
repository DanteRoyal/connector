package com.connector.api.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.connector.api.global.auth.LoginInterceptor;
import com.connector.api.service.JwtProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

	private final JwtProvider jwtProvider;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor(jwtProvider))
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("api/v1/users/register");
	}
}
