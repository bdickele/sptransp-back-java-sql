package org.bdickele.sptransp.exception;

import org.apache.log4j.Logger;

/**
 * Base class for customized exceptions of the application
 * Created by Bertrand DICKELE
 */
public class SpTranspException extends RuntimeException {

    private static final Logger LOGGER = Logger.getLogger(SpTranspException.class);

    private final SpTranspError error;


    public SpTranspException(SpTranspError error, String message) {
        super(message);
        this.error = error;
    }

    public SpTranspException(SpTranspError error, Throwable cause, String message) {
        super(message, cause);
        this.error = error;
    }

    public SpTranspError getError() {
        return error;
    }
}
