package com.amaris.exception;

import com.amaris.common.utils.GlobalConstants;
import com.amaris.dto.error.ErrorResponse;
import com.amaris.exception.impl.NotAllowException;
import com.amaris.exception.impl.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(400, GlobalConstants.VALIDATION_ERROR, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handlesNotFoundException(NotFoundException ex) {
        int statusCode = HttpStatus.NOT_FOUND.value();
        String message = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(statusCode, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotAllowException.class})
    protected ResponseEntity<Object> handleNotAllowException(NotAllowException exception) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String message = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(statusCode, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
