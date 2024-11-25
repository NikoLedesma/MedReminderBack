package com.utn.api.medreminderback.controller;

import com.utn.api.medreminderback.exception.MedAlreadyExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MedAlreadyExisteException.class)
    public ResponseEntity<String> handleMedAlreadyExisteException(MedAlreadyExisteException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // Status 409
                .body(ex.getMessage());
    }
}