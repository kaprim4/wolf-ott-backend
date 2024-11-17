package com.wolfott.auth.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterResponse(@JsonProperty("access_token") String accessToken,
                               @JsonProperty("refresh_token") String refreshToken,
                               @JsonProperty("refresh_expires_in") Long expires,
                               @JsonProperty("token_type") String tokenType,
                               @JsonProperty("session_state") String sessionState,
                               @JsonProperty("scope") String scope) {
}
