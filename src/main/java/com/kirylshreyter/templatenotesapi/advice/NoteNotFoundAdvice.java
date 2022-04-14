package com.kirylshreyter.templatenotesapi.advice;

import com.kirylshreyter.templatenotesapi.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoteNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noteNotFoundHandler(NoteNotFoundException ex) {
        return ex.getMessage();
    }
}
