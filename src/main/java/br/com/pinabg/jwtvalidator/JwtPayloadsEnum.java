package br.com.pinabg.jwtvalidator;

public enum JwtPayloadsEnum {
	VALID_PAYLOAD("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "}"),
	INVALID_PAYLOAD("abc"),
	INVALID_PAYLOAD_NUMBER_CLAIM_NAME("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Ton1nh0 Araujo\""
    		+ "}"),
    INVALID_PAYLOAD_MORE_3_CLAIMS("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "  \"CEP\": \"027.37.010\""
    		+ "}");
	private final String payload;

    JwtPayloadsEnum(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
