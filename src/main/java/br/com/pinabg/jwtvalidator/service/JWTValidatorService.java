package br.com.pinabg.jwtvalidator.service;

import org.springframework.stereotype.Service;

@Service
public class JWTValidatorService {
	public boolean stringIsNotNullOrEmpty(String name) {
	    return !(name == null || name.isBlank());
	}
}
