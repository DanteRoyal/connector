package com.connector.api.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {

	/*
	 * TODO: 환경변수 설정
	 * */
	private final String SECRET_KEY = "secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey";

	public String createToken(final String userId) {
		final Date now = new Date(System.currentTimeMillis());
		final Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 3L);

		return Jwts.builder()
			.subject(userId)
			.issuedAt(now)
			.expiration(expiration)
			.signWith(getSignInKey(), Jwts.SIG.HS256)
			.compact();
	}

	public String extractToken(final HttpServletRequest request) {
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorization != null && authorization.startsWith("Bearer ")) {
			return authorization.substring(7);
		}

		return null;
	}

	public String extractUserId(final String token) {
		final Claims claims = Jwts.parser()
			.verifyWith(getSignInKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();

		return claims.getSubject();
	}

	public boolean validateToken(final String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token);
			log.info("true");
			return !claims.getPayload().getExpiration().before(new Date());
		} catch (Exception e) {
			/* ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
				SignatureException, IllegalArgumentException
			 * TODO 각 예외별 로 exception handling하기
			 * */
			log.info("false");
			return false;
		}
	}

	private SecretKey getSignInKey() {
		final byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
