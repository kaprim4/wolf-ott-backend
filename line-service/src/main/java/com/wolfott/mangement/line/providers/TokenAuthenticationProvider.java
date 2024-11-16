package com.wolfott.mangement.line.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public interface TokenAuthenticationProvider extends AuthenticationProvider, AuthenticationConverter {
    String generateToken(Authentication authentication);
    boolean validateToken(String token);
    Authentication getAuthenticationFromToken(String token);
}

