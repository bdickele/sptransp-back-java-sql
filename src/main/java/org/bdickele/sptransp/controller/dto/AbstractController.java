package org.bdickele.sptransp.controller.dto;

import org.bdickele.sptransp.exception.SpTranspError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Bertrand DICKELE
 */
public abstract class AbstractController {

    @ExceptionHandler(SpTranspException.class)
    public ResponseEntity<String> somethingWrong(SpTranspException e) {
        SpTranspError error = e.getError();
        return new ResponseEntity<String>(e.getMessage(), error.getHttpStatus());
    }
}
