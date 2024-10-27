package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    private LocalDateTime time;               // Time of the error
    private String path;                       // Path where the error occurred
    private String message;                    // General error message
    private List<FieldError> fieldErrors;     // List of field-specific errors

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        private String field;                   // Field name
        private String message;                 // Field-specific error message
    }
}
