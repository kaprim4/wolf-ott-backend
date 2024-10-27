package com.wolfott.mangement.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericErrorResponse {
    private LocalDateTime time; // Time of the error
    private String path;         // Path where the error occurred
    private String message;      // Error message
    private String code;         // Error code (optional)
    private HttpStatus status;   // HTTP status
}
