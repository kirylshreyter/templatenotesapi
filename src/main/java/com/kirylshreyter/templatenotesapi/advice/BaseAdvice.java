package com.kirylshreyter.templatenotesapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;

@ControllerAdvice
public class BaseAdvice {
    protected ResponseEntity<?> getErrors(Exception exception, HttpStatus status) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return new ResponseEntity<>(errors, status);
    }
}
