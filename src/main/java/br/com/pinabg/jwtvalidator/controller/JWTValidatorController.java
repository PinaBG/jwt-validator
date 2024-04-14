package br.com.pinabg.jwtvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pinabg.jwtvalidator.service.JWTValidatorService;

@RestController
@RequestMapping("/main")
public class JWTValidatorController {
	
	@Autowired
	private JWTValidatorService jwtValidatorService;
	
	@RequestMapping("/greetings")
	public String getGreetings() {
		return "Hello World!";
	}
}
