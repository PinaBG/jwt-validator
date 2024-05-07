package br.com.pinabg.jwtvalidator.service;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtCreationService {
	@Value("${jwt.secret}")
    private String jwtKey;
	
	public String createJwt(String payload) {
		SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes());
		
		return Jwts.builder()
                .content(payload)
                .signWith(key)
                .compact();
	}
}
