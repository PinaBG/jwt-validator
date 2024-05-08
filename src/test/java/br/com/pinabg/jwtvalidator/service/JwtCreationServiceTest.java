package br.com.pinabg.jwtvalidator.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pinabg.jwtvalidator.enumeration.JwtPayloadsEnum;

@SpringBootTest
class JwtCreationServiceTest {
	@Autowired
	private JwtCreationService creationService;
	
	private static List<String> payloads = new ArrayList<>();
	
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
	
	@Test
    void testCreateJwt() {
		for(String payload: payloads) {
			assertFalse(creationService.createJwt(payload).isEmpty());
		}
	}
}
