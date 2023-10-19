package com.example.moviesmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<AuthError> handleException (Exception exc) {
        AuthError err = new AuthError();
        err.setMessage(exc.getMessage() +"Error class:" + exc.getClass());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
