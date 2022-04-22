package com.kirylshreyter.templatenotesapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user with id ".concat(id.toString()));
    }

    public UserNotFoundException(String email) {
        super("Could not find user with email ".concat(email));
    }
}
