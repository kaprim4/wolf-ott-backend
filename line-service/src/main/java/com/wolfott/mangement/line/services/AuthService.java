package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;

public interface AuthService extends AuthorizationManager<User>, AuthenticationManager {
}
