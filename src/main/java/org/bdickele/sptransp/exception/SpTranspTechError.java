package org.bdickele.sptransp.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by bdickele
 */
public enum SpTranspTechError implements SpTranspError {

    UNEXPECTED_ERROR(1, "Unexpected error: %s"),

    OPERATION_USER_MISSING(50, "Creation or update user is missing");


    private final int errorCode;

    private final String errorMessage;

    private final HttpStatus httpStatus;


    /**
     * Constructor
     * @param errorCode
     * @param errorMessage
     */
    private SpTranspTechError(int errorCode, String errorMessage) {
        this(errorCode, errorMessage, HttpStatus.BAD_REQUEST);
    }

    private SpTranspTechError(int errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorCodePrefix() {
        return "TECH";
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
