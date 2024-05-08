package br.com.pinabg.jwtvalidator.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.pinabg.jwtvalidator.enumeration.JwtResponseDescriptionEnum;
import br.com.pinabg.jwtvalidator.model.JwtResponseModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Service
public class ValidateClaimsService {
	@Value("${jwt.secret}")
    private String jwtKey;
	
	public JwtResponseModel loadAllValidations(String jwsToken) {
	    JwtResponseModel jwtResponse = new JwtResponseModel();
	    jwtResponse.setValidity("Verdadeiro");
	    jwtResponse.setDescription("Justificativa: Abrindo o JWT, as informações contidas atendem a descrição:");
	    
	    ValidateJwtService validateJwtService = new ValidateJwtService(jwtKey);
	    try {
	    	Claims claims = validateJwtService.getJwtClaims(jwsToken);
	    	processClaims(claims, jwtResponse);
	    }catch(JwtException e) {
	    	updateResponseForInvalid(jwtResponse, "Justificativa: JWT inválido.");
	    }
	    
	    return jwtResponse;
	}

	private void processClaims(Claims claims, JwtResponseModel jwtResponse) {
		if (claims != null) {
	        jwtResponse.setJwtPayload(claims.toString());
	        
	        if (!processJwtClaims(claims, jwtResponse)
	        	    || !processClaimName(claims, jwtResponse)
	        	    || !processClaimRole(claims, jwtResponse)
	        	    || !processClaimSeed(claims, jwtResponse)) {
	        	    return;
	        }
	    }
	}

	private boolean processJwtClaims(Claims claims, JwtResponseModel jwtResponse) {
		if (!jwtContainsMoreOrLessThan3Claims(claims)) {
			updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_MORE_3_CLAIMS.getPayload());
			return false;
		} else if (!jwtContainsNecessaryClaims(claims)) {
			updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS.getPayload());
			return false;
		}
		return true;
	}
	
	private boolean processClaimName(Claims claims, JwtResponseModel jwtResponse) {
		if (!stringIsNotNullOrEmpty(claims.get("Name", String.class))) {
			updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_NAME.getPayload());
			return false;
		} else if (!nameDontContainNumbers(claims.get("Name", String.class))) {
			updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_NUMBER_CLAIM_NAME.getPayload());
			return false;
		} else if (!nameLenghtLessThan256Characters(claims.get("Name", String.class))) {
			updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_NAME_MORE_256_CHARACTERS.getPayload());
			return false;
		}
		return true;
	}
	
	private boolean processClaimRole(Claims claims, JwtResponseModel jwtResponse) {
		if (!stringIsNotNullOrEmpty(claims.get("Role", String.class))) {
		    updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_ROLE.getPayload());
		    return false;
		} else if (!roleHasRightFormat(claims.get("Role", String.class))) {
		    updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE.getPayload());
		    return false;
		}
		return true;
	}
	
	private boolean processClaimSeed(Claims claims, JwtResponseModel jwtResponse) {
		int seed = Integer.parseInt(claims.get("Seed", String.class));
		if (!validateSeed(seed)) {
		    updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME.getPayload());
		    return false;
		}
		return true;
	}
	
	private void updateResponseForInvalid(JwtResponseModel jwtResponse, String justification) {
	    jwtResponse.setValidity("Falso");
	    jwtResponse.setDescription(justification);
	}
	
	public boolean jwtContainsMoreOrLessThan3Claims(Claims claims) {
		return claims.size()==3;
	}

	public boolean jwtContainsNecessaryClaims(Claims claims) {
		return claims.containsKey("Name") && claims.containsKey("Role") && claims.containsKey("Seed");
	}
	
	public boolean stringIsNotNullOrEmpty(String value) {
	    return value != null && !value.isBlank();
	}
	
	public boolean nameDontContainNumbers(String name) {
		return !Pattern.compile("\\d+").matcher(name).find();
	}
	
	public boolean nameLenghtLessThan256Characters(String name) {
		return name.length()<=256;
	}
	
	public boolean roleHasRightFormat(String role) {
		return role.equals("Admin") || role.equals("Member") || role.equals("External");
	}
	
	public boolean validateSeed(int seed) {
		if(seed == 1 || seed == 4) {
			return false;
		}
		
		for (int i = 2; i < Math.sqrt(seed); i++){ 
			if (seed % i == 0) 
				return false;
		} 
		return true; 
	}
}
