package br.com.pinabg.jwtvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pinabg.jwtvalidator.model.JwtResponseModel;
import br.com.pinabg.jwtvalidator.service.JwtCreationService;
import br.com.pinabg.jwtvalidator.service.ValidateClaimsService;

@RestController
@RequestMapping("/main")
public class JwtValidatorController {
	
	@Autowired
	private ValidateClaimsService validateClaimsService;
	@Autowired
	private JwtCreationService jwtCreation;
	
	@RequestMapping("/greetings")
	public String getGreetings() {
		return "Hello World!";
	}
	
	@GetMapping("/validatejwt")
	public ResponseEntity<JwtResponseModel> validaJwt(@RequestParam String jwt) {
		validateClaimsService.loadAllValidations(jwt);
		JwtResponseModel jwtResponse = validateClaimsService.loadAllValidations(jwt);
		
		return ResponseEntity.ok(jwtResponse);
	}
	
	@PostMapping("/createjwt")
	public String createJwt(@RequestBody String jsonPayload) {
		return jwtCreation.createJwt(jsonPayload);
	}
}
