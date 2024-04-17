package br.com.pinabg.jwtvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pinabg.jwtvalidator.model.JwtResponseModel;
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
	
	@GetMapping("/validajwt")
	public ResponseEntity<JwtResponseModel> validaJwt(@RequestParam String jwt) {
		jwtValidatorService.loadAllValidations(jwt);
		JwtResponseModel jwtResponse = jwtValidatorService.loadAllValidations(jwt);
		
		return ResponseEntity.ok(jwtResponse);
	}
}
