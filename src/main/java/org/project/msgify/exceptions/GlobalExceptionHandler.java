package org.project.msgify.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleUsernameDuplicateException(DataIntegrityViolationException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Username already exists. Try something else.");
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleUsernameViolationException(ConstraintViolationException e){
        String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Invalid input");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }
}
