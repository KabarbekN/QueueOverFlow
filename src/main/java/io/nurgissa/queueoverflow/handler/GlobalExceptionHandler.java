package io.nurgissa.queueoverflow.handler;

import io.nurgissa.queueoverflow.exceptions.NotSamePasswordsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotSamePasswordsException.class)
    public ResponseEntity<?> handleNotSamePasswordException(NotSamePasswordsException notSamePasswordsException){
        return ResponseEntity
                .badRequest()
                .body(notSamePasswordsException.getMessage());
    }
}
