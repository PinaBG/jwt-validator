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
	
	@Test
	void testNameValidate() {
		String wrongName = "t3st" + "a".repeat(256);
		boolean resultFalse = jwtValidatorService.validateName(wrongName);
		assertFalse(resultFalse);
		
		String rightName = "teste";
		boolean resultTrue = jwtValidatorService.validateName(rightName);
		assertTrue(resultTrue);
	}
	
	@Test
	void testRoleHasRightFormat() {
		String wrongRole = "Cliente";
		boolean resultFalse = jwtValidatorService.roleHasRightFormat(wrongRole);
		assertFalse(resultFalse);
		
		String rightRoleAdmin = "Admin";
		boolean resultTrueAdmin = jwtValidatorService.roleHasRightFormat(rightRoleAdmin);
		assertTrue(resultTrueAdmin);
		
		String rightRoleMember = "Member";
		boolean resultTrueMember = jwtValidatorService.roleHasRightFormat(rightRoleMember);
		assertTrue(resultTrueMember);
		
		String rightRoleExternal = "External";
		boolean resultTrueExternal = jwtValidatorService.roleHasRightFormat(rightRoleExternal);
		assertTrue(resultTrueExternal);
	}
	
	@Test
	void testValidateRole() {
		String wrongRole = "";
		boolean resultFalse = jwtValidatorService.validateRole(wrongRole);
		assertFalse(resultFalse);
		
		String rightRole = "Admin";
		boolean resultTrue = jwtValidatorService.validateRole(rightRole);
		assertTrue(resultTrue);
	}
	
	@Test
	void testValidateSeed() {
		int wrongSeed = 6;
		boolean resultFalse = jwtValidatorService.validateSeed(wrongSeed);
		assertFalse(resultFalse);
		
		int rightSeed = 2;
		boolean resultTrue = jwtValidatorService.validateSeed(rightSeed);
		assertTrue(resultTrue);
	}
}