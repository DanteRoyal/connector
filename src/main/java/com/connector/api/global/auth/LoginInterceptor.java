package com.connector.api.global.auth;

import org.springframework.web.servlet.HandlerInterceptor;

import com.connector.api.service.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	private final JwtProvider jwtProvider;

	public LoginInterceptor(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = jwtProvider.extractToken(request);
		log.info("token = {}", token);
		
		if (token != null && jwtProvider.validateToken(token)) {
			return true;
		}
		return false;
	}
}
