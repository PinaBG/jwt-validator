package br.com.pinabg.jwtvalidator.service;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;

public class ValidateJwtService {
	@NonNull
	private final SecretKey key;

	@Autowired
	public ValidateJwtService(String jwtKey) {
        this.key = Keys.hmacShaKeyFor(jwtKey.getBytes());
    }

	public Claims getJwtClaims(String jwsToken) throws JwtException {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(jwsToken)
				.getPayload();
	}
}
