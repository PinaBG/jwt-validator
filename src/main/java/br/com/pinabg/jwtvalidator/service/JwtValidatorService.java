package br.com.pinabg.jwtvalidator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.pinabg.jwtvalidator.enumeration.JwtResponseDescriptionEnum;
import br.com.pinabg.jwtvalidator.model.JwtResponseModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtValidatorService {
	@Value("${jwt.secret}")
    private String jwtKey;
	
	public String createJwt(String payload) {
		SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes());
		
		return Jwts.builder()
                .content(payload)
                .signWith(key)
                .compact();
	}
	
	public JwtResponseModel loadAllValidations(String jwsToken) {
	    JwtResponseModel jwtResponse = new JwtResponseModel();
	    jwtResponse.setValidity("Verdadeiro");
	    jwtResponse.setDescription("Justificativa: Abrindo o JWT, as informações contidas atendem a descrição:");

	    Claims claims = validateJwt(jwsToken, jwtResponse);

	    if (claims != null) {
	        String payload = claims.toString();
	        jwtResponse.setJwtPayload(payload);

	        if (!jwtContainsMoreOrLessThan3Claims(claims)) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_MORE_3_CLAIMS.getPayload());
	        } else if (!jwtContainsNecessaryClaims(claims)) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS.getPayload());
	        } else if (!stringIsNotNullOrEmpty(claims.get("Name", String.class))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_NAME.getPayload());
	        } else if (!nameDontContainNumbers(claims.get("Name", String.class))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_NUMBER_CLAIM_NAME.getPayload());
	        } else if (!nameLenghtLessThan256Characters(claims.get("Name", String.class))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_NAME_MORE_256_CHARACTERS.getPayload());
	        } else if (!stringIsNotNullOrEmpty(claims.get("Role", String.class))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_BLANK_CLAIM_ROLE.getPayload());
	        } else if (!roleHasRightFormat(claims.get("Role", String.class))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE.getPayload());
	        } else if (!validateSeed(Integer.parseInt(claims.get("Seed", String.class)))) {
	            updateResponseForInvalid(jwtResponse, JwtResponseDescriptionEnum.INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME.getPayload());
	        }
	    }

	    return jwtResponse;
	}
	
	private void updateResponseForInvalid(JwtResponseModel jwtResponse, String justification) {
	    jwtResponse.setValidity("Falso");
	    jwtResponse.setDescription(justification);
	}

	private Claims validateJwt(String jwsToken, JwtResponseModel jwtResponse) {
		try {
			return getJwtClaims(jwsToken);
	    } catch (JwtException e) {
	    	jwtResponse.setValidity("Falso");
	    	jwtResponse.setDescription("Justificativa: JWT inválido.");
	    }
		return null;
	}
	
	public Claims getJwtClaims(String jwsToken) throws JwtException {
		SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes());
		
		return Jwts.parser()
		        .verifyWith(key)
		        .build()
		        .parseSignedClaims(jwsToken)
		        .getPayload();
	}
	
	public boolean jwtContainsMoreOrLessThan3Claims(Claims claims) {
		boolean isValid = true;
		if(claims.size()!=3) {
			isValid = false;
		}
		return isValid;
	}

	public boolean jwtContainsNecessaryClaims(Claims claims) {
		boolean isValid = true;
		if (!claims.containsKey("Name") || !claims.containsKey("Role") || !claims.containsKey("Seed")) {
			isValid = false;
		}
		return isValid;
	}
	
	public boolean stringIsNotNullOrEmpty(String name) {
	    return !(name == null || name.isBlank());
	}
	
	public boolean nameDontContainNumbers(String name) {
		boolean isValid = true;
		
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()) {
			isValid = false;
		}
		return isValid;
	}
	
	public boolean nameLenghtLessThan256Characters(String name) {
		return name.length()<=256;
	}
	
	public boolean roleHasRightFormat(String role) {
		boolean isValid = true;
		
		final String ADMIN = "Admin";
		final String MEMBER = "Member";
		final String EXTERNAL = "External";
		
		if(!role.equals(ADMIN) && !role.equals(MEMBER) && !role.equals(EXTERNAL)) {
			isValid = false;
		}
		
		return isValid;
	}
	
	public boolean validateSeed(int seed) {
		boolean isValid = true;
		
		if(seed == 1 || seed == 4) {
			return false;
		}
		
		for (int i = 2; i < Math.sqrt(seed); i++){ 
			if (seed % i == 0) 
				isValid  = false; 
		} 
		return isValid; 
	}
}
