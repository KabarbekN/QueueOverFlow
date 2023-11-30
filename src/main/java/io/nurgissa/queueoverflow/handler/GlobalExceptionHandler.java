package io.nurgissa.queueoverflow.handler;

import io.nurgissa.queueoverflow.exceptions.NotFoundException;
import io.nurgissa.queueoverflow.exceptions.NotSamePasswordsException;
import io.nurgissa.queueoverflow.exceptions.ServiceException;
import io.nurgissa.queueoverflow.exceptions.TagExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotSamePasswordsException.class)
    public ResponseEntity<?> handleNotSamePasswordException(NotSamePasswordsException notSamePasswordsException){
        return ResponseEntity
                .badRequest()
                .body(notSamePasswordsException.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity
                .badRequest()
                .body(usernameNotFoundException.getMessage());
    }
    @ExceptionHandler(TagExistException.class)
    public ResponseEntity<?> handleTagExistException(TagExistException exception){
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception){
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleNotFoundException(IllegalStateException exception){
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(ServiceException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
