package com.connector.api.global;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.connector.api.global.auth.AuthArgumentResolver;
import com.connector.api.global.auth.JwtInterceptor;
import com.connector.api.service.JwtProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

	private final JwtProvider jwtProvider;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor(jwtProvider))
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("/api/v1/users/register")
			.excludePathPatterns("/api/v1/auth");
		/*
		 * TODO: 프로필 상세, 리스트는 어떻게 처리할지?
		 *
		 * */
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new AuthArgumentResolver(jwtProvider));

	}
}
