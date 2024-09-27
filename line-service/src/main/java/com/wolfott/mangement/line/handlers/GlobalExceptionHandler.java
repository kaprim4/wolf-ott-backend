package com.wolfott.mangement.line.handlers;

import com.wolfott.mangement.line.exceptions.BouquetNotFoundException;
import com.wolfott.mangement.line.responses.GenericErrorResponse;
import com.wolfott.mangement.line.responses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BouquetNotFoundException.class)
    public ResponseEntity<GenericErrorResponse> handleBouquetNotFoundException(BouquetNotFoundException ex, WebRequest request) {
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
}
