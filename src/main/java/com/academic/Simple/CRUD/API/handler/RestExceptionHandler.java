package com.academic.Simple.CRUD.API.handler;

import com.academic.Simple.CRUD.API.model.error.ErrorMessage;
import com.academic.Simple.CRUD.API.model.exception.BadRequestException;
import com.academic.Simple.CRUD.API.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBaRequestException(BadRequestException exception) {
        ErrorMessage error = new ErrorMessage("Bad Request", HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
