package org.bdickele.sptransp.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.IllegalFormatException;

/**
 * Interface for business and technical errors
 * Created by bdickele
 */
public interface SpTranspError {

    String getErrorCodePrefix();

    int getErrorCode();

    HttpStatus getHttpStatus();

    String getRawMessage();

    /**
     * @return For instance, if error code is 130, it returns "0130"
     */
    default String getFormattedErrorCode() {
        return getErrorCodePrefix() + String.format("%04d", getErrorCode());
    }

    /**
     * @param args Message arguments
     * @return Formatted error code and formatted message, for instance "BIZ0100 - Agreement rule already exists for..."
     */
    default String getFormattedMessage(Object... args) {
        String rawMessage = getRawMessage();

        String formattedMessage;
        try {
            formattedMessage = String.format(rawMessage, args);
        } catch(IllegalFormatException e) { //NOSONAR
            formattedMessage = rawMessage + ". Error with args: " + StringUtils.join(args, ", ");
        }

        return getFormattedErrorCode() + " - " + formattedMessage;
    }

    default SpTranspException exception(Object... args) {
        return new SpTranspException(this, getFormattedMessage(args));
    }

    default SpTranspException exception(Throwable cause, Object... args) {
        return new SpTranspException(this, cause, getFormattedMessage(args));
    }
}
