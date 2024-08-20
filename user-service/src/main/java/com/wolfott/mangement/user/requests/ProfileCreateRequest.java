package com.wolfott.mangement.user.requests;

public record ProfileCreateRequest(
        String profileName,
        String profileOptions,
        Boolean active
) {
}
