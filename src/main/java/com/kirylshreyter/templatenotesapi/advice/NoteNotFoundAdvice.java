package com.kirylshreyter.templatenotesapi.advice;

import com.kirylshreyter.templatenotesapi.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoteNotFoundAdvice extends BaseAdvice {
    @ResponseBody
    @ExceptionHandler(NoteNotFoundException.class)
    ResponseEntity<?> noteNotFoundHandler(NoteNotFoundException ex) {
        return getErrors(ex, HttpStatus.NOT_FOUND);
    }
}
