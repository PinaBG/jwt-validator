package br.com.pinabg.jwtvalidator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pinabg.jwtvalidator.enumeration.JwtPayloadsEnum;
import br.com.pinabg.jwtvalidator.enumeration.JwtResponseDescriptionEnum;
import br.com.pinabg.jwtvalidator.model.JwtResponseModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JwtValidatorControllerTest {
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static List<String> payloads = new ArrayList<>();
    private static List<String> responses = new ArrayList<>();
    private static List<String> createdJwt = new ArrayList<>();
    
    @BeforeAll
    static void createJwtByPayloads() {
    	payloads.add(JwtPayloadsEnum.VALID_PAYLOAD.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_BLANK_CLAIM_NAME.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_NUMBER_CLAIM_NAME.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_MORE_3_CLAIMS.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_BLANK_CLAIM_ROLE.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE.getPayload());
    	payloads.add(JwtPayloadsEnum.INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME.getPayload());
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
    @Order(1)
    void testCreateJwt() {
    	for(String payload: payloads) {
    		ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    "http://localhost:" + port + "/main/createjwt",
                    payload,
                    String.class);

            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            createdJwt.add(responseEntity.getBody());
    	}
    }

    @Test
    @Order(2)
    void testValidaJwt() {
    	assertFalse(createdJwt.isEmpty());
    	for(String jwt: createdJwt) {
	        ResponseEntity<JwtResponseModel> responseEntity = restTemplate.getForEntity(
	                "http://localhost:" + port + "/main/validatejwt?jwt={jwt}",
	                JwtResponseModel.class,
	                jwt);
	
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        JwtResponseModel responseBody = responseEntity.getBody();
	        assertTrue(responses.contains(responseBody.getDescription()));
	        responses.remove(responseBody.getDescription());
    	}
    }
}
