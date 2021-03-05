package com.trade_accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({NotFoundEntityException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundExceptions(NotFoundEntityException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionResponse response =
                new ExceptionResponse(status, ex.getMessage());

        return new ResponseEntity<>(response, status);
    }
}
