package org.bdickele.sptransp.exception;

/**
 * Created by bdickele
 */
public enum SpTranspTechError implements SpTranspError {

    OPERATION_USER_MISSING(50, "Creation or update user is missing");


    private final int errorCode;

    private final String errorMessage;


    /**
     * Constructor
     * @param errorCode
     * @param errorMessage
     */
    private SpTranspTechError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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
    public String getRawMessage() {
        return errorMessage;
    }
}
