package br.com.pinabg.jwtvalidator;

public enum JwtResponseDescriptionEnum {
	VALID_PAYLOAD("Justificativa: Abrindo o JWT, as informações contidas atendem a descrição:"),
	INVALID_PAYLOAD("Justificativa: JWT inválido."),
	INVALID_PAYLOAD_BLANK_CLAIM_NAME("Justificativa: Abrindo o JWT, a Claim Name é vazia."),
	INVALID_PAYLOAD_NUMBER_CLAIM_NAME("Justificativa: Abrindo o JWT, a Claim Name possui caracter de números."),
	INVALID_PAYLOAD_NAME_MORE_256_CHARACTERS("Justificativa: Abrindo o JWT, a Claim Name possui mais de 256 caracteres."),
	INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS("Justificativa: Abrindo o JWT, não foram encontradas todas as claims necessárias."),
	INVALID_PAYLOAD_MORE_3_CLAIMS("Justificativa: Abrindo o JWT, mais/menos de 3 claims."),
	INVALID_PAYLOAD_BLANK_CLAIM_ROLE("Justificativa: Abrindo o JWT, a Claim Role é vazia."),
	INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE("Justificativa: Abrindo o JWT, a Claim Role não possui um dos tres valores ((Admin, Member e External)."),
	INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME("Justificativa: Abrindo o JWT, a Claim Seed não é um número primo.");
	
	private final String response;

	JwtResponseDescriptionEnum(String response) {
        this.response = response;
    }

    public String getPayload() {
        return response;
    }
}
