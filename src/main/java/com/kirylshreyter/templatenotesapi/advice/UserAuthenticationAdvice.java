package com.kirylshreyter.templatenotesapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAuthenticationAdvice extends BaseAdvice {
    @ResponseBody
    @ExceptionHandler(DisabledException.class)
    ResponseEntity<?> userDisabledExceptionHandler(DisabledException exception) {
        return getErrors(exception, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<?> userBadCredentialsExceptionHandler(BadCredentialsException exception) {
        return getErrors(exception, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<?> userLockedExceptionHandler(LockedException exception) {
        return getErrors(exception, HttpStatus.UNAUTHORIZED);
    }
}
