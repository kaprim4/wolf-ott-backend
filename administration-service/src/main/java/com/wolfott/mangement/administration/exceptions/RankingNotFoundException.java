package com.wolfott.mangement.administration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RankingNotFoundException extends RuntimeException {
    public RankingNotFoundException() {
        super("Ranking Not Found");
    }
    public RankingNotFoundException(String message) {
        super(message);
    }
}
