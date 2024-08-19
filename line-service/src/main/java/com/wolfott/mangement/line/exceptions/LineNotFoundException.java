package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LineNotFoundException extends RuntimeException {

    public LineNotFoundException() {
        super("Line Not Found");
    }

    public LineNotFoundException(String message) {
        super(message);
    }
}
