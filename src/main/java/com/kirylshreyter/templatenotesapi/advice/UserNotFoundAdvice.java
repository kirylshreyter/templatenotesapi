package com.kirylshreyter.templatenotesapi.advice;

import com.kirylshreyter.templatenotesapi.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserNotFoundAdvice extends BaseAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<?> userNotFoundHandler(UserNotFoundException ex) {
        return getErrors(ex, HttpStatus.NOT_FOUND);
    }
}
