package br.com.pinabg.jwtvalidator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class JWTValidatorService {
	public boolean stringIsNotNullOrEmpty(String name) {
	    return !(name == null || name.isBlank());
	}
	
	public boolean nameDontContainsNumbers(String name) {
		boolean isValid = true;
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()) {
			isValid = false;
		}
		return isValid;
	}
}
