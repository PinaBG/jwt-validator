package br.com.pinabg.jwtvalidator.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pinabg.jwtvalidator.enumeration.JwtResponseDescriptionEnum;
import br.com.pinabg.jwtvalidator.model.JwtResponseModel;
import io.jsonwebtoken.Claims;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ValidateClaimsServiceTest {
	
	@Autowired
	private ValidateClaimsService jwtValidatorService;
	
	@Mock
    private Claims claims;
	
    private static List<String> responses = new ArrayList<>();
    private static List<String> jwts = new ArrayList<>();
    
    @BeforeAll
    static void createJwts() {
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICI3ODQxIiwKICAiTmFtZSI6ICJUb25pbmhvIEFyYXVqbyIKfQ.HxIokwwabZBEukG2qQvHU9h3QNB8P-MbFTpdf98EvA0");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ImFiYyI.HDjb5COa1ChHYr3losZQ1Pb_JuO2pZeFARAumCG09Zk");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICI3ODQxIiwKICAiTmFtZSI6ICIiCn0.qSOo4pSc-XtbyWeCXasPKQeTaXeC7urth7h0cKQU9Dc");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICI3ODQxIiwKICAiTmFtZSI6ICJUb24xbmgwIEFyYXVqbyIKfQ.4YWIHd_g83sqKMrhIRndjyLPYxMnnHhi6_tFbRXPUA0");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICI3ODQxIiwKICAiQ0VQIjogIjAyNy4zNy4wMTAiCn0.En5iY51NbDxnIt4xnH4T64k8cS3nDno9JWZHuMWwuyM");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICI3ODQxIiwKICAiTmFtZSI6ICJUb25pbmhvIEFyYXVqbyIsCiAgIkNFUCI6ICIwMjcuMzcuMDEwIgp9.8_e_8XiR0BEiII9n1doQNuWoA663fLtYBTCvtEHkwpQ");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIiIsCiAgIlNlZWQiOiAiNzg0MSIsCiAgIk5hbWUiOiAiVG9uaW5obyBBcmF1am8iCn0.lv0aHKhycM7PCICod2DO7NbuY-q1yMqZ7RPpJMqNg6I");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogImJsYWJsYSIsCiAgIlNlZWQiOiAiNzg0MSIsCiAgIk5hbWUiOiAiVG9uaW5obyBBcmF1am8iCn0.wlzsk8rNmu4r2jWByjU9Og6kNebID9-Tc1B9DIpIFEI");
    	jwts.add("eyJhbGciOiJIUzI1NiJ9.ewogICJSb2xlIjogIkFkbWluIiwKICAiU2VlZCI6ICIxMCIsCiAgIk5hbWUiOiAiVG9uaW5obyBBcmF1am8iCn0.wLdx2cblfpc3u5q5AX-QqR1UtdR1gkmjHYJIOn_nq3c");
    }
    
    @BeforeAll
    static void createJwtResponses() {
    	responses.add(JwtResponseDescriptionEnum.VALID_PAYLOAD.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_NAME.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_NUMBER_CLAIM_NAME.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_MORE_3_CLAIMS.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_ROLE.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE.getPayload());
    	responses.add(JwtResponseDescriptionEnum.INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME.getPayload());
    }
    
    @Test
    void testLoadAllValidations() {
    	JwtResponseModel responseModel = new JwtResponseModel();
    	for(String jwt: jwts) {
    		responseModel = jwtValidatorService.loadAllValidations(jwt);
    		assertTrue(responses.contains(responseModel.getDescription()));
    		responses.remove(responseModel.getDescription());
    	}
    }
    
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
}