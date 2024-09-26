package com.wolfott.mangement.line.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PresetNotFoundException extends RuntimeException {
    public PresetNotFoundException() {
        super("Preset Not Found");
    }

    public PresetNotFoundException(String message) {
        super(message);
    }
}
