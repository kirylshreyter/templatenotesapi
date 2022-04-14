package com.kirylshreyter.templatenotesapi.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long id) {
        super("Could not find note ".concat(id.toString()));
    }
}
