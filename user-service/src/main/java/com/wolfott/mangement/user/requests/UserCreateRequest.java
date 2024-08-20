package com.wolfott.mangement.user.requests;

import java.util.Date;

public record UserCreateRequest(
        String username,
//        String password,
        String email,
        String ip,
//        Long memberGroupId,
        Float credits,
        String notes,
        Boolean status,
        String resellerDns,
        Long ownerId,
        String overridePackages,
        String hue,
        Integer theme,
        String timezone,
        String apiKey,
        Date lastLogin,
        Date dateRegistered
) { }