package com.wolfott.mangement.line.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public interface SessionAuthenticationProvider extends AuthenticationProvider, AuthenticationConverter {
    String createSession(Authentication authentication);
    boolean validateSession(String sessionId);
    Authentication getAuthenticationFromSession(String sessionId);
}
