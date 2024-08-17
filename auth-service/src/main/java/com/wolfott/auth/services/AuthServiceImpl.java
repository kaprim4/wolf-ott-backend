package com.wolfott.auth.services;

import com.wolfott.auth.exceptions.InvalidCredentialsException;
import com.wolfott.auth.exceptions.UserNotFoundException;
import com.wolfott.auth.models.User;
import com.wolfott.auth.repositories.UserRepository;
import com.wolfott.auth.requests.LoginRequest;
import com.wolfott.auth.responses.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.username();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String plainPassword = request.password();
        String hashedPassword = user.getPassword();
        try {
            boolean validPassword = validatePassword(plainPassword, hashedPassword);
            if (!validPassword)
                throw new InvalidCredentialsException();
            return new LoginResponse("Access Token", "Refresh Token");
        } catch (NoSuchAlgorithmException e) {
            log.error("Uncaught Exception in Login : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // Method to hash a password using SHA-512 with the given salt and rounds
    public static String hashPassword(String password, String salt, int rounds) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());

        byte[] hashed = md.digest(password.getBytes());
        for (int i = 1; i < rounds; i++) {
            md.reset();
            md.update(hashed);
            hashed = md.digest();
        }

        return Base64.getEncoder().encodeToString(hashed);
    }

    // Method to validate a password against the stored hashed password
    public static boolean validatePassword(String plainPassword, String hashedPassword) throws NoSuchAlgorithmException {
        // Extract information from the hashedPassword
        Pattern pattern = Pattern.compile("^\\$6\\$rounds=(\\d+)\\$([a-zA-Z0-9./]+)\\$([a-zA-Z0-9./]+)$");
        Matcher matcher = pattern.matcher(hashedPassword);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid hashed password format");
        }

        int rounds = Integer.parseInt(matcher.group(1));
        String salt = matcher.group(2);
        String storedHash = matcher.group(3);

        // Generate the hash with the same salt and rounds
        String computedHash = hashPassword(plainPassword, salt, rounds);

        return computedHash.equals(storedHash);
    }
}
