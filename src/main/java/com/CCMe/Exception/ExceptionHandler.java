package com.CCMe.Exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(ApiException.class)
    public ResponseEntity<HttpErrorResponse> handleException(ApiException e) {
        log.info("Handle ApiException: {}", e.getMessage());
        var response = HttpErrorResponse.of(e.getMessage(),e.getStatus(),e.getErrors(),null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatus()));
    }
}
