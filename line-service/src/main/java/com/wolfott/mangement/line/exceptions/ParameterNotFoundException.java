package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParameterNotFoundException extends RuntimeException {

    public ParameterNotFoundException() {
        super("Parameter Not Found");
    }

    public ParameterNotFoundException(String message) {
        super(message);
    }
}
