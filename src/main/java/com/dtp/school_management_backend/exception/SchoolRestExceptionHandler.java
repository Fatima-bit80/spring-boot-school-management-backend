package com.dtp.school_management_backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchoolRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IllegalAccessErrorResponse> handleException(IllegalAccessException exc) {
        IllegalAccessErrorResponse error = new IllegalAccessErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
