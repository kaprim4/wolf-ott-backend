package com.wolfott.auth.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    public PasswordUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return (rawPassword, hashedPassword) ->
                rawPassword != null && hashedPassword != null && passwordEncoder.matches(rawPassword, hashedPassword);
    }

    @FunctionalInterface
    public interface PasswordValidator {
        boolean validate(String rawPassword, String hashedPassword);
    }
}
