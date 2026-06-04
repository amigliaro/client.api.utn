package com.client.api.exceptions;

import com.client.api.dto.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorException> handleNotFound(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorException(404, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorException(406, ex.getMessage()));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorException> httpClientErrorException(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorException(503, ex.getMessage()));
    }


}
