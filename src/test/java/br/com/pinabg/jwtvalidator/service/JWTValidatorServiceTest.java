package br.com.pinabg.jwtvalidator.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;

@SpringBootTest
class JWTValidatorServiceTest {
	
	@Autowired
	private JWTValidatorService jwtValidatorService;
	
	@Mock
    private Claims claims;

	@Test
	void testStringBlankSpace() {
		final String wrongStringBlankSpace = "   ";
		boolean resultFalseBlank = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringBlankSpace);

		assertFalse(resultFalseBlank);
	}

	@Test
	void testStringZeroLenght() {
		final String wrongStringZeroLenght = "";
		boolean resultFalseZeroLength = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringZeroLenght);

		assertFalse(resultFalseZeroLength);
	}

	@Test
	void testStringNull() {
		final String wrongStringNull = null;
		boolean resultFalseNull = jwtValidatorService.stringIsNotNullOrEmpty(wrongStringNull);

		assertFalse(resultFalseNull);
	}

	@Test
	void testValidString() {
		final String rightString = "test";
		boolean resultTrue = jwtValidatorService.stringIsNotNullOrEmpty(rightString);

		assertTrue(resultTrue);
	}
	
	@Test
	void testNameContainsNumbers() {
		final String wrongName = "T3st1ng";
		boolean resultFalse = jwtValidatorService.nameDontContainNumbers(wrongName);
		assertFalse(resultFalse);
		
		final String rightName = "Testing";
		boolean resultTrue = jwtValidatorService.nameDontContainNumbers(rightName);
		assertTrue(resultTrue);
	}
	
	@Test
	void testNameLengthMoreThan256() {
		final String wrongName = "a".repeat(257);
		boolean resultFalse = jwtValidatorService.nameLenghtLessThan256Characters(wrongName);
		assertFalse(resultFalse);
		
		final String rightName = "abc";
		boolean resultTrue = jwtValidatorService.nameLenghtLessThan256Characters(rightName);
		assertTrue(resultTrue);
	}
	
	@Test
	void testNameValidate() {
		final String wrongName = "t3st" + "a".repeat(256);
		boolean resultFalse = jwtValidatorService.validateName(wrongName);
		assertFalse(resultFalse);
		
		final String rightName = "teste";
		boolean resultTrue = jwtValidatorService.validateName(rightName);
		assertTrue(resultTrue);
	}
	
	@Test
	void testRoleHasRightFormat() {
		final String wrongRole = "Cliente";
		boolean resultFalse = jwtValidatorService.roleHasRightFormat(wrongRole);
		assertFalse(resultFalse);
		
		final String rightRoleAdmin = "Admin";
		boolean resultTrueAdmin = jwtValidatorService.roleHasRightFormat(rightRoleAdmin);
		assertTrue(resultTrueAdmin);
		
		final String rightRoleMember = "Member";
		boolean resultTrueMember = jwtValidatorService.roleHasRightFormat(rightRoleMember);
		assertTrue(resultTrueMember);
		
		final String rightRoleExternal = "External";
		boolean resultTrueExternal = jwtValidatorService.roleHasRightFormat(rightRoleExternal);
		assertTrue(resultTrueExternal);
	}
	
	@Test
	void testValidateRole() {
		final String wrongRole = "";
		boolean resultFalse = jwtValidatorService.validateRole(wrongRole);
		assertFalse(resultFalse);
		
		final String rightRole = "Admin";
		boolean resultTrue = jwtValidatorService.validateRole(rightRole);
		assertTrue(resultTrue);
	}
	
	@Test
	void testValidateSeed() {
		final int wrongSeed = 6;
		boolean resultFalse = jwtValidatorService.validateSeed(wrongSeed);
		assertFalse(resultFalse);
		
		final int rightSeed = 2;
		boolean resultTrue = jwtValidatorService.validateSeed(rightSeed);
		assertTrue(resultTrue);
	}
	
	@Test
	void jwtContainsLessThan3ClaimsTest() {
		when(claims.size()).thenReturn(4);
	    assertFalse(jwtValidatorService.jwtContainsMoreOrLessThan3Claims(claims));

	    when(claims.size()).thenReturn(2);
	    assertFalse(jwtValidatorService.jwtContainsMoreOrLessThan3Claims(claims));

	    when(claims.size()).thenReturn(3);
	    assertTrue(jwtValidatorService.jwtContainsMoreOrLessThan3Claims(claims));
	}
	
	@Test
	void jwtContainsNecessaryClaimsTest() {
	    assertFalse(jwtValidatorService.jwtContainsNecessaryClaims(getClaims(false, true, true)));

	    assertTrue(jwtValidatorService.jwtContainsNecessaryClaims(getClaims(true, true, true)));
	}

	private Claims getClaims(boolean namePresent, boolean rolePresent, boolean seedPresent) {
	    when(claims.containsKey("Name")).thenReturn(namePresent);
	    when(claims.containsKey("Role")).thenReturn(rolePresent);
	    when(claims.containsKey("Seed")).thenReturn(seedPresent);
	    return claims;
	}
	
//	@Test
//	void getJwtClaimsTest() {
//		assertThrows(JwtException.class, () -> jwtValidatorService.getJwtClaims("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.KqLDSGFPM3W9YMbJ_6QBthQVcudFrz32qh79eGkZPj0"));
//        
//		Claims result = jwtValidatorService.getJwtClaims("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.9RxbnTDfhT_h7NXTRclmFLR7zBs3P0cDPx9w1Rstiec");
//	    assertNotNull(result);
//	    assertTrue(result instanceof Claims);
//	}
}