package org.project.msgify.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.project.msgify.dto.ErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDto> handleDuplicateException(DataIntegrityViolationException e){
        String errorMessage = Objects.requireNonNull(e.getRootCause()).getMessage();
        if(errorMessage.contains("Key (email)")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDto("There is an another account associated with this email.", System.currentTimeMillis()));
        } else {
            // Username unique violation is the only other case.
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDto("Username already exists. Try something else.", System.currentTimeMillis()));
        }
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorDto> handleUsernameViolationException(ConstraintViolationException e){
        String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Invalid input");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(errorMessage, System.currentTimeMillis()));
    }

    @ExceptionHandler({LoginFailedException.class})
    public ResponseEntity<ErrorDto> handleLoginFailedException(LoginFailedException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDto("Invalid Credentials.", System.currentTimeMillis()));
    }
}
