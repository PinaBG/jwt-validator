package br.com.pinabg.jwtvalidator.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JWTValidatorServiceTest {
	
	@Autowired
	private JWTValidatorService jwtValidatorService;

	@Test
	void testStringBlankSpace() {
		String wrongStringBlankSpace = "   ";
		boolean resultFalseBlank = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringBlankSpace);

		assertFalse(resultFalseBlank);
	}

	@Test
	void testStringZeroLenght() {
		String wrongStringZeroLenght = "";
		boolean resultFalseZeroLength = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringZeroLenght);

		assertFalse(resultFalseZeroLength);
	}

	@Test
	void testStringNull() {
		String wrongStringNull = null;
		boolean resultFalseNull = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringNull);

		assertFalse(resultFalseNull);
	}

	@Test
	void testValidString() {
		String rightString = "test";
		boolean resultTrue = jwtValidatorService.stringIsNotNullOrEmpty(rightString);

		assertTrue(resultTrue);
	}
}