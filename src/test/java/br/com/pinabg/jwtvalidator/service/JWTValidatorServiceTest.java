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
	
	@Test
	void testNameContainsNumbers() {
		String wrongName = "T3st1ng";
		boolean resultFalse = jwtValidatorService.nameDontContainNumbers(wrongName);
		assertFalse(resultFalse);
		
		String rightName = "Testing";
		boolean resultTrue = jwtValidatorService.nameDontContainNumbers(rightName);
		assertTrue(resultTrue);
	}
	
	@Test
	void testNameLengthMoreThan256() {
		String wrongName = "a".repeat(257);
		boolean resultFalse = jwtValidatorService.nameLenghtLessThan256Characters(wrongName);
		assertFalse(resultFalse);
		
		String rightName = "abc";
		boolean resultTrue = jwtValidatorService.nameLenghtLessThan256Characters(rightName);
		assertTrue(resultTrue);
	}
}