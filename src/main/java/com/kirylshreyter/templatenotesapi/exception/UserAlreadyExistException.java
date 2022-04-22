package com.kirylshreyter.templatenotesapi.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super("An account for that email already exists.");
    }
}
