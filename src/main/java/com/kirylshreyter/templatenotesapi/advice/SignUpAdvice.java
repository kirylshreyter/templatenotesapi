package com.kirylshreyter.templatenotesapi.advice;

import com.kirylshreyter.templatenotesapi.exception.UserAlreadyExistException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SignUpAdvice extends BaseAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    ResponseEntity<?> noteNotFoundHandler(UserAlreadyExistException ex) {
        return getErrors(ex, HttpStatus.NOT_FOUND);
    }
}
