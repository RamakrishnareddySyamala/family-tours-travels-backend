package com.familytours.familytourstravels.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;


    @RestControllerAdvice
    public class GlobalExceptionHandler {

  
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidationErrors(
                MethodArgumentNotValidException ex) {

            Map<String, Object> errors = new HashMap<>();

            ex.getBindingResult()
              .getFieldErrors()
              .forEach(error ->
                  errors.put(error.getField(), error.getDefaultMessage())
              );

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        @ExceptionHandler(HandlerMethodValidationException.class)
        public ResponseEntity<Map<String, Object>> handleMethodValidationErrors(
                HandlerMethodValidationException ex) {

            Map<String, Object> response = new HashMap<>();

            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Validation failed");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }
