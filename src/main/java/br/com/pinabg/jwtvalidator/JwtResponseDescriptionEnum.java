package br.com.pinabg.jwtvalidator;

public enum JwtResponseDescriptionEnum {
	VALID_PAYLOAD("Justificativa: Abrindo o JWT, as informações contidas atendem a descrição:"),
	INVALID_PAYLOAD("Justificativa: JWT inválido"),
	INVALID_PAYLOAD_NUMBER_CLAIM_NAME("Justificativa: Abrindo o JWT, a Claim Name possui caracter de números."),
    INVALID_PAYLOAD_MORE_3_CLAIMS("Justificativa: Abrindo o JWT, mais/menos de 3 claims.");
	
	private final String response;

	JwtResponseDescriptionEnum(String response) {
        this.response = response;
    }

    public String getPayload() {
        return response;
    }
}
