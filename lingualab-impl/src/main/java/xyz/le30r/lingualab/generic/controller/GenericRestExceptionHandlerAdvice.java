package xyz.le30r.lingualab.generic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.le30r.lingualab.generic.ObjectNotFoundException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GenericRestExceptionHandlerAdvice {
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Void> handleNotFoundException(ObjectNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
