package br.com.pinabg.jwtvalidator.model;

import lombok.Data;

@Data
public class JwtResponseModel {
	private String validity;
	private String description;
	private String jwtPayload;
}
