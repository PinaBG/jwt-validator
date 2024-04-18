package br.com.pinabg.jwtvalidator;

public enum JwtPayloadsEnum {
	VALID_PAYLOAD("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "}"),
	INVALID_PAYLOAD("abc"),
	INVALID_PAYLOAD_BLANK_CLAIM_NAME("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"\""
    		+ "}"),
	INVALID_PAYLOAD_NUMBER_CLAIM_NAME("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Ton1nh0 Araujo\""
    		+ "}"),
	INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_CLAIMS("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"CEP\": \"027.37.010\""
    		+ "}"),
    INVALID_PAYLOAD_MORE_3_CLAIMS("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\","
    		+ "  \"CEP\": \"027.37.010\""
    		+ "}"),
	INVALID_PAYLOAD_BLANK_CLAIM_ROLE("{"
    		+ "  \"Role\": \"\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "}"),
	INVALID_PAYLOAD_DOES_NOT_CONTAIN_NECESSARY_ROLE("{"
    		+ "  \"Role\": \"blabla\","
    		+ "  \"Seed\": \"7841\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "}"),
	INVALID_PAYLOAD_CLAIM_SEED_NOT_PRIME("{"
    		+ "  \"Role\": \"Admin\","
    		+ "  \"Seed\": \"10\","
    		+ "  \"Name\": \"Toninho Araujo\""
    		+ "}");
	private final String payload;

    JwtPayloadsEnum(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
