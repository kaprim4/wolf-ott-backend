package com.wolfott.stream_mangement.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EpisodeNotFoundException extends RuntimeException {
    public EpisodeNotFoundException(String message) {
        super(message);
    }
}