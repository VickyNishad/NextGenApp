package com.nextgen.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NGRException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestNotFoundException.class)
    protected ResponseEntity<?> requestNotfoundException(RequestNotFoundException ex) {
        // TODO Auto-generated method stub
        APIError error = new APIError();
        error.setStatus(ex.getStatus());
        error.setErrorMessage(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemErrorException.class)
    protected ResponseEntity<?> systemErrorException(SystemErrorException ex) {
        // TODO Auto-generated method stub
        APIError error = new APIError();
        error.setStatus(ex.getStatus());
        error.setErrorMessage(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        // TODO Auto-generated method stub
        APIError error = new APIError();
        error.setStatus(ex.getStatus());
        error.setErrorMessage(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
