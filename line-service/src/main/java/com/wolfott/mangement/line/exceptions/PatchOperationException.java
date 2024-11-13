package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatchOperationException extends RuntimeException {
    public PatchOperationException(String message){
        super(message);
    }
    public PatchOperationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
