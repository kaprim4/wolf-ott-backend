package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BouquetNotFoundException extends RuntimeException {
    public BouquetNotFoundException(String message) {
        super(message);
    }
}
