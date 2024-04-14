package br.com.pinabg.jwtvalidator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class JWTValidatorService {
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
