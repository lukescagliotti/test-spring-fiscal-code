package com.example.codicefiscale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(InvalidCodiceFiscaleException.class)
    public ResponseEntity<ApiError> handleInvalidCodiceFiscale(InvalidCodiceFiscaleException ex) {
        ApiError error = new ApiError(ex.getErrorCode(), ex.getErrorInfo());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
