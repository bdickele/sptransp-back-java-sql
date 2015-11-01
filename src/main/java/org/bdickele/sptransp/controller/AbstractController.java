package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.exception.SpTranspError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Bertrand DICKELE
 */
public abstract class AbstractController {

    public static final String TEMP_USER_UID = "TEMP";


    @ExceptionHandler(SpTranspException.class)
    public ResponseEntity<String> somethingWrong(SpTranspException e) {
        SpTranspError error = e.getError();
        return new ResponseEntity<>(e.getMessage(), error.getHttpStatus());
    }
}
