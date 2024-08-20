package com.wolfott.mangement.user.requests;

public record ProfileUpdateRequest(
        Long profileId,
        String profileName,
        String profileOptions,
        Boolean active
) {
}
