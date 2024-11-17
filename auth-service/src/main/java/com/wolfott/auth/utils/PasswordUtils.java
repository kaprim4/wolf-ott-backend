package com.wolfott.auth.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordUtils {
    private PasswordUtils() {}
    // Method to hash a password using SHA-512 with the given salt and rounds (mimicking the PHP `crypt` function)
    public static String hashPassword(String password, String salt, int rounds) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashed = md.digest(password.getBytes(StandardCharsets.UTF_8));
        for (int i = 1; i < rounds; i++) {
            md.reset();
            md.update(hashed);
            hashed = md.digest();
        }

        return Base64.getEncoder().encodeToString(hashed);
    }

    // Method to validate a password against the stored hashed password (format: $6$rounds=20000$salt$hashed_password)
    public static boolean validatePassword(String plainPassword, String hashedPassword) {
        try {
            // Regular expression to parse the hashed password
            Pattern pattern = Pattern.compile("^\\$6\\$rounds=(\\d+)\\$([a-zA-Z0-9./]+)\\$([a-zA-Z0-9./]+)$");
            Matcher matcher = pattern.matcher(hashedPassword);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid hashed password format");
            }

            // Extract rounds, salt, and stored hash
            int rounds = Integer.parseInt(matcher.group(1));
            String salt = matcher.group(2);
            String storedHash = matcher.group(3);

            // Hash the plain password with the same salt and rounds
            String computedHash = hashPassword(plainPassword, salt, rounds);

            // Compare the computed hash with the stored hash
            return computedHash.equals(storedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
