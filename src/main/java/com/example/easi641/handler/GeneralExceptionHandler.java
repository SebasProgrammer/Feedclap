package com.example.easi641.handler;

import com.example.easi641.exception.ExceptionResponse;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception exception,
                                                     WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(), request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()
        );
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleIncorrectRequest(NotFoundException exception,
                                                         WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(), request.getDescription(false),
                HttpStatus.BAD_REQUEST, LocalDateTime.now()
        );
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleNotFound(BadRequestException exception,
                                                 WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(), request.getDescription(false),
                HttpStatus.NOT_FOUND, LocalDateTime.now()
        );
        return new ResponseEntity<>(response, response.getStatus());
    }
}
