package org.bdickele.sptransp.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by bdickele
 */
public enum SpTranspBizError implements SpTranspError {

    UNEXPECTED_ERROR(1, "Unexpected error: %s"),

    // ================================================================
    // AUTHENTICATION / AUTHORIZATION
    // ================================================================

    NOT_AUTHENTICATED(50, "User is not authenticated", HttpStatus.UNAUTHORIZED),

    NOT_AUTHORIZED(51, "User is not authorized to perform that operation", HttpStatus.FORBIDDEN),

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

    COULD_NOT_FIND_LAST_APPLIED_VISA(203, "Could not find last visa applied for the request"),

    EMPLOYEE_HAS_ALREADY_APPLIED_A_VISA(204, "User %s has already granted/denied a visa for that request"),

    // ================================================================
    // EMPLOYEE
    // ================================================================

    EMPLOYEE_NOT_FOUND(300, "Employee not found", HttpStatus.NOT_FOUND),

    EMPLOYEE_MISSING_VALUE(301, "Following value is missing for employee: %s"),

    EMPLOYEE_INCORRECT_SENIORITY(302, "Incorrect value for seniority: it should be between %s and %s");


    private final int errorCode;

    private final String errorMessage;

    private final HttpStatus httpStatus;


    /**
     * Constructor
     * @param errorCode
     * @param errorMessage
     */
    private SpTranspBizError(int errorCode, String errorMessage) {
        this(errorCode, errorMessage, HttpStatus.BAD_REQUEST);
    }

    private SpTranspBizError(int errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
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
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getRawMessage() {
        return errorMessage;
    }
}
