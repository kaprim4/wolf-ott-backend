package com.wolfott.auth.responses;

public record LoginResponse(String accessToken, String refreshToken) {
}
