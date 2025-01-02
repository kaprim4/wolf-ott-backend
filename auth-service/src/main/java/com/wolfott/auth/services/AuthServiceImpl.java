package com.wolfott.auth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.auth.exceptions.UserNotFoundException;
import com.wolfott.auth.models.User;
import com.wolfott.auth.models.UserGroup;
import com.wolfott.auth.repositories.UserGroupRepository;
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

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;

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

        InetAddress myIP;
        String ipAdresse = "";
        try {
            myIP = InetAddress.getLocalHost();
            ipAdresse = myIP.getHostAddress();
        } catch (Exception e) {
            log.error(" [ IMPORT SERVICE ] - [ ERROR WHILE TRYING TO GET THE IP ADDRESS, ERROR: {} ]", e.getMessage());
        }

        user.setIp(ipAdresse);
        user.setLastLogin(new Date());
        userRepository.saveAndFlush(user);

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
        log.info("password: {} getPassword: {}", password, user.getPassword());
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

        Optional<UserGroup> userGroup = userGroupRepository.findByUserId(user.getId());
        userGroup.ifPresent(group -> {
            Map<String, Object> groupClaims = new HashMap<>();
            groupClaims.put("groupId", group.getGroupId());
            groupClaims.put("groupName", group.getGroupName());
            groupClaims.put("isAdmin", group.getIsAdmin());
            groupClaims.put("isReseller", group.getIsReseller());
            groupClaims.put("totalAllowedGenTrials", group.getTotalAllowedGenTrials());
            groupClaims.put("totalAllowedGenIn", group.getTotalAllowedGenIn());
            groupClaims.put("deleteUsers", group.getDeleteUsers());
            groupClaims.put("allowedPages", group.getAllowedPages());
            groupClaims.put("canDelete", group.getCanDelete());
            groupClaims.put("createSubResellers", group.getCreateSubResellers());
            groupClaims.put("createSubResellersPrice", group.getCreateSubResellersPrice());
            groupClaims.put("resellerClientConnectionLogs", group.getResellerClientConnectionLogs());
            groupClaims.put("canViewVod", group.getCanViewVod());
            groupClaims.put("allowDownload", group.getAllowDownload());
            groupClaims.put("minimumTrialCredits", group.getMinimumTrialCredits());
            groupClaims.put("allowRestrictions", group.getAllowRestrictions());
            groupClaims.put("allowChangeUsername", group.getAllowChangeUsername());
            groupClaims.put("allowChangePassword", group.getAllowChangePassword());
            groupClaims.put("minimumUsernameLength", group.getMinimumUsernameLength());
            groupClaims.put("minimumPasswordLength", group.getMinimumPasswordLength());
            groupClaims.put("allowChangeBouquets", group.getAllowChangeBouquets());
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Long> subResellersList = objectMapper.readValue(group.getSubResellers(), List.class);
                groupClaims.put("subResellers", subResellersList);
            } catch (JsonProcessingException e) {
                groupClaims.put("subResellers", new ArrayList<>());
                System.err.println("Erreur lors de la conversion de subResellers : " + e.getMessage());
            }
            claims.put("group", groupClaims);
        });

        claims.put("credits", user.getCredits());
        claims.put("registrationDate", user.getDateRegistered());

        int line_sold = userRepository.getCountLineByMemberId(user.getId());
        claims.put("line_sold", line_sold);

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
