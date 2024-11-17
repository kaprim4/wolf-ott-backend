package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.UserThemeOptions;
import com.wolfott.mangement.user.requests.UserThemeOptionsRequest;

import java.util.Optional;

public interface UserThemeOptionsService {
    Optional<UserThemeOptions> getThemeOptions(Long id);

    UserThemeOptions create(UserThemeOptionsRequest request);

    UserThemeOptions update(UserThemeOptionsRequest request);
}
