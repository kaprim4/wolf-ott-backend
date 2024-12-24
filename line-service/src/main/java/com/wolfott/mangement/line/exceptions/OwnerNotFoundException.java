package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException() {
        super("Owner Not Found");
    }

    public OwnerNotFoundException(String message) {
        super(message);
    }
}
