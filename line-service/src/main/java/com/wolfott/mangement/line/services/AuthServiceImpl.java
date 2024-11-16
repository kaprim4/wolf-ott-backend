package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

//@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private AuthenticationConverter authenticationConverter;

    @Override
    public void verify(Supplier<Authentication> authentication, User user) {
        AuthService.super.verify(authentication, user);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, User user) {
        return null;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
