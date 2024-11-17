package com.wolfott.auth.services;

import com.wolfott.auth.requests.LoginRequest;
import com.wolfott.auth.responses.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    LoginResponse register(LoginRequest request);
}
