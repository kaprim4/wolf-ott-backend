package com.wolfott.mangement.administration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException() {
        super("Application Not Found");
    }
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
