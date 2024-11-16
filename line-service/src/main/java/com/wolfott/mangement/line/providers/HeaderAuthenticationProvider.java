package com.wolfott.mangement.line.providers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

public class HeaderAuthenticationProvider implements AuthenticationProvider, AuthenticationConverter {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        return null;
    }
/*
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Extract user info from headers (e.g., X-User-Id, X-Roles)
        String userId = (String) authentication.getDetails(); // this can be the X-User-Id header

        // Retrieve user details from your user service or database
        UserDetails user = userService.loadUserByUsername(userId);

        // Here you can set roles or authorities based on the user's roles
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
*/
//    @Override
//    public Authentication validateAuthentication(Authentication authentication) {
//        return null;
//    }
//
//    @Override
//    public Authentication getAuthentication(HttpServletRequest request) {
//        return null;
//    }
}

