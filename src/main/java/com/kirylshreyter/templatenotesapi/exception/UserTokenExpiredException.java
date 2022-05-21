package com.kirylshreyter.templatenotesapi.exception;

public class UserTokenExpiredException extends RuntimeException {
    public UserTokenExpiredException(String message) {
        super(message);
    }
}
