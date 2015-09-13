package org.bdickele.sptransp.exception;

import org.apache.log4j.Logger;

/**
 * Base class for customized exceptions of the application
 * Created by Bertrand DICKELE
 */
public class SpTranspException extends RuntimeException {

    private static final Logger LOGGER = Logger.getLogger(SpTranspException.class);


    public SpTranspException(String message) {
        super(message);
    }

    public SpTranspException(String message, Throwable cause) {
        super(message, cause);
    }
}
