package xyz.le30r.lingualab.auth.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.le30r.lingualab.auth.exception.InviteExpiredException;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
public class AuthRestControllerExceptionHandlerAdvice {
    @ExceptionHandler(InviteExpiredException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<Void> handleInviteExpiredException(InviteExpiredException exception) {
        return ResponseEntity.status(CONFLICT).build();
    }
}
