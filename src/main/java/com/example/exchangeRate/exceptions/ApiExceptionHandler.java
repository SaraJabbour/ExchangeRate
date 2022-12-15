package com.example.exchangeRate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguments(IllegalArgumentException exception, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }


    @ExceptionHandler(InsufficientDataException.class)
    public ResponseEntity<String> handleInsufficientData(InsufficientDataException exception, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> handleNoDataFound(NoDataFoundException exception, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(RateAlreadyExistsException.class)
    public ResponseEntity<String> handleRateAlreadyExists(RateAlreadyExistsException exception, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(exception.getMessage());
    }

    @ExceptionHandler(RateNotFoundException.class)
    public ResponseEntity<String> handleRateNotFound(RateNotFoundException exception, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
