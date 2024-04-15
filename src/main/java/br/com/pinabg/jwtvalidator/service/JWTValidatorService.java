package br.com.pinabg.jwtvalidator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

@Service
public class JWTValidatorService {
	@Value("${jwt.secret}")
    private String jwtKey;
	
	public boolean validateJWT(String jwsToken) {
		try {
	        Claims claims = getJwtClaims(jwsToken);
	        return jwtContainsMoreOrLessThan3Claims(claims) && jwtContainsNecessaryClaims(claims);
	    } catch (JwtException e) {
	        return false;
	    }
	}
	
	public boolean jwtContainsMoreOrLessThan3Claims(Claims claims) {
		boolean isValid = true;
		if(claims.size()!=3) {
			isValid = false;
		}
		return isValid;
	}

	public boolean jwtContainsNecessaryClaims(Claims claims) {
		boolean isValid = true;
		if (!claims.containsKey("Name") || !claims.containsKey("Role") || !claims.containsKey("Seed")) {
			isValid = false;
		}
		return isValid;
	}

	public Claims getJwtClaims(String jwsToken) throws JwtException {
		Password key = Keys.password(jwtKey.toCharArray());
		
		return Jwts.parser()
		        .verifyWith(key)
		        .build()
		        .parseSignedClaims(jwsToken)
		        .getPayload();
	}
	
	public boolean validateName(String name) {
		return stringIsNotNullOrEmpty(name) && nameDontContainNumbers(name) && nameLenghtLessThan256Characters(name);
	}
	
	public boolean stringIsNotNullOrEmpty(String name) {
	    return !(name == null || name.isBlank());
	}
	
	public boolean nameDontContainNumbers(String name) {
		boolean isValid = true;
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()) {
			isValid = false;
		}
		return isValid;
	}
	
	public boolean nameLenghtLessThan256Characters(String name) {
		return name.length()<=256;
	}
	
	public boolean validateRole(String role) {
		return stringIsNotNullOrEmpty(role) && roleHasRightFormat(role);
	}
	
	public boolean roleHasRightFormat(String role) {
		boolean isValid = true;
		
		final String ADMIN = "Admin";
		final String MEMBER = "Member";
		final String EXTERNAL = "External";
		
		if(!role.equals(ADMIN) && !role.equals(MEMBER) && !role.equals(EXTERNAL)) {
			isValid = false;
		}
		
		return isValid;
	}
	
	public boolean validateSeed(int seed) {
		boolean isValid = true;
		
		if(seed == 1 || seed == 4) {
			return false;
		}
		
		for (int i = 2; i < Math.sqrt(seed); i++){ 
			if (seed % i == 0) 
				isValid  = false; 
		} 
		return isValid; 
	}
}
