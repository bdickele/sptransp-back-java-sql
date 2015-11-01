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

    AGR_RULE_ALREADY_EXISTS(100, "Agreement rule already exists for destination [%s] and good [%s]"),

    AGR_RULE_DOESNT_EXIST(101, "Agreement rule for these destination [%s] and goods [%s] codes doesn't exist", HttpStatus.NOT_FOUND),

    AGR_RULE_MISSING_VALUE(102, "Following value is missing for agreement rule: %s"),

    // ================================================================
    // REQUEST
    // ================================================================

    REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA(200, "Request doesn't expect any agreement visa anymore"),

    VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE(201, "Visa [%s / %s] cannot be applied as next expected one is [%s / %s]"),

    COULD_NOT_FIND_NEXT_EXPECTED_AGREEMENT_VISA(202, "Could not find next expected agreement visa for the request"),

    COULD_NOT_FIND_LAST_APPLIED_VISA(203, "Could not find last visa applied for the request"),

    EMPLOYEE_HAS_ALREADY_APPLIED_A_VISA(204, "User %s has already granted/denied a visa for that request"),

    REQUEST_NOT_FOUND_FOR_REFERENCE(205, "Request not found for reference %s"),

    // ================================================================
    // EMPLOYEE
    // ================================================================

    EMPLOYEE_NOT_FOUND(300, "Employee not found for UID %s", HttpStatus.NOT_FOUND),

    EMPLOYEE_MISSING_VALUE(301, "Following value is missing for employee: %s"),

    EMPLOYEE_INCORRECT_SENIORITY(302, "Incorrect value for seniority: it should be between %s and %s"),

    // ================================================================
    // CUSTOMER
    // ================================================================

    CUSTOMER_NOT_FOUND(400, "Customer not found for code [%s]", HttpStatus.NOT_FOUND),

    CUSTOMER_MISSING_VALUE(401, "Following value is missing for customer: %s"),

    // ================================================================
    // GOODS
    // ================================================================

    GOODS_NOT_FOUND(500, "Goods not found for code [%s]", HttpStatus.NOT_FOUND),

    // ================================================================
    // DESTINATION
    // ================================================================

    DESTINATION_NOT_FOUND(600, "Destination not found for code [%s]", HttpStatus.NOT_FOUND),

    // ================================================================
    // REQUEST
    // ================================================================

    REQUEST_MISSING_VALUE(700, "Following value is missing for request: %s"),

    DESTINATION_AND_ARRIVAL_ARE_THE_SAME(701, "Departure and arrival are the same"),

    REQUEST_NOT_ALLOWED(702, "We are not allowed to send %s to destination %s"),

    REQUEST_NOT_FOUND(703, "Request not found for reference [%s]", HttpStatus.NOT_FOUND),;


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
