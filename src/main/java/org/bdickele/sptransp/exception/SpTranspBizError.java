package org.bdickele.sptransp.exception;

/**
 * Created by bdickele
 */
public enum SpTranspBizError implements SpTranspError {

    UNEXPECTED_ERROR(1, "Unexpected error: %s"),

    // ================================================================
    // AGREEMENT RULE
    // ================================================================

    AGREEMENT_RULE_ALREADY_EXISTS(100, "Agreement rule already exists for destination [%s] and good [%s]"),

    // ================================================================
    // REQUEST
    // ================================================================

    REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA(200, "Request doesn't expect any agreement visa anymore"),

    VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE(201, "Visa [%s / %s] cannot be applied as next expected one is [%s / %s]"),

    COULD_NOT_FIND_NEXT_EXPECTED_AGREEMENT_VISA(202, "Could not find next expected agreement visa for the request"),

    COULD_NOT_FIND_LAST_APPLIED_VISA(203, "Could not find last visa applied for the request");


    private final int errorCode;

    private final String errorMessage;


    /**
     * Constructor
     * @param errorCode
     * @param errorMessage
     */
    private SpTranspBizError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCodePrefix() {
        return "BIZ";
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getRawMessage() {
        return errorMessage;
    }
}
