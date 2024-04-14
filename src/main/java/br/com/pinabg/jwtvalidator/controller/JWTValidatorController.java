package br.com.pinabg.jwtvalidator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class JWTValidatorController {
	@RequestMapping("/greetings")
	public String getGreetings() {
		return "Hello World!";
	}
}
