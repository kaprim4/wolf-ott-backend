package com.wolfott.mangement.administration.handlers;

import com.wolfott.mangement.administration.exceptions.UserNotFoundException;
import com.wolfott.mangement.administration.responses.GenericErrorResponse;
import com.wolfott.mangement.administration.responses.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        GenericErrorResponse errorResponse = new GenericErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false), // Path where the error occurred
                ex.getMessage(),
                "BOUQUET_NOT_FOUND", // Error code
                HttpStatus.NOT_FOUND // HTTP status
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // For validation errors
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        List<ValidationErrorResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationErrorResponse.FieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false), // Path where the error occurred
                "Validation failed",
                fieldErrors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorResponse> handleException(Exception ex, WebRequest request) {
        GenericErrorResponse errorResponse = new GenericErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false), // Path where the error occurred
                ex.getMessage(),
                "INTERNAL_SERVER_ERROR", // Error code
                HttpStatus.INTERNAL_SERVER_ERROR // HTTP status
        );
        log.error("Uncaught Exception", ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
