package com.wolfott.auth.services;

import com.wolfott.auth.exceptions.UserNotFoundException;
import com.wolfott.auth.models.User;
import com.wolfott.auth.repositories.UserRepository;
import com.wolfott.auth.requests.LoginRequest;
import com.wolfott.auth.responses.LoginResponse;
import com.wolfott.auth.utils.PasswordUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordUtils.PasswordValidator passwordValidator;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenValidity}")
    private long accessTokenValidity;

    public AuthServiceImpl(PasswordUtils.PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.username();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        String plainPassword = request.password();

        String accessToken = createToken(user);
        String refreshToken = UUID.randomUUID().toString();
        return new LoginResponse(
                accessToken,
                refreshToken,
                accessTokenValidity,
                "Bearer",
                "Active",
                "User"
        );
    }

    @Override
    public LoginResponse register(LoginRequest request) {
        return null;
    }

    @Override
    public boolean validatePassword(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return passwordValidator.validate(password, user.getPassword());
    }

    public String createToken(@NotNull User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("typ", "Bearer");
        claims.put("iss", "https://wolfguard.sbs");
        claims.put("jti", UUID.randomUUID().toString());
        Map<String, Object> roleAccess = new HashMap<>();
        String role = null;
        List<String> roles = new ArrayList<>();
        if (user.getGroup() != null) {
            role = user.getGroup().getGroupName();
            roleAccess.put("roles", List.of(role));
        }
        claims.put("role_access", roleAccess);
        claims.put("role", role);
        claims.put("isAdmin", user.getGroup().getIsAdmin());
        claims.put("scope", "user");
        claims.put("sid", user.getId());
        claims.put("email_verified", true);
        claims.put("name", user.getUsername());
        claims.put("preferred_username", user.getUsername());
        claims.put("email", user.getEmail());

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
