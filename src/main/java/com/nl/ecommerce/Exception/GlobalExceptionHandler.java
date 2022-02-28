package com.nl.ecommerce.Exception;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> BadRequestHandling (BadRequestException e, WebRequest request) {//request is the path from controller class
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false) );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> NotFoundHandling (NotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> GlobalExceptionHandling (Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
