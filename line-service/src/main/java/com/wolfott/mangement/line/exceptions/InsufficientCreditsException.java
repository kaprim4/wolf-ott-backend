package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientCreditsException extends RuntimeException {
    public InsufficientCreditsException(String message) {
        super(message);
    }
}
