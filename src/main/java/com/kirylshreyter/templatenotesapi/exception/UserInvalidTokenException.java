package com.kirylshreyter.templatenotesapi.exception;

public class UserInvalidTokenException extends RuntimeException {
    public UserInvalidTokenException(String message) {
        super(message);
    }
}
