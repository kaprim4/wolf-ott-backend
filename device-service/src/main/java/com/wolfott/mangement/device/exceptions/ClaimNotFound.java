package com.wolfott.mangement.device.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClaimNotFound extends RuntimeException {
    public ClaimNotFound(String message) {
        super(message);
    }
}
