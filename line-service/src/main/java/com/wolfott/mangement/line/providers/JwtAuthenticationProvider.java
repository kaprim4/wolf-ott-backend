package com.wolfott.mangement.line.providers;

import com.wolfott.mangement.line.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;


public class JwtAuthenticationProvider implements TokenAuthenticationProvider {

    private final String secretKey = "8e8f25c7-ad06-47ef-8681-08ae79fa7df4"; // Should be securely stored in a properties or env variable
    private final long validityInMilliseconds = 3600000; // Token validity period (1 hour)

    /**
     * Generates a JWT token for the given authentication.
     * @param authentication the authentication object containing user details
     * @return generated JWT token as a String
     */
    @Override
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        // Generate and return JWT token
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * Validates a JWT token.
     * @param token the JWT token to validate
     * @return true if valid, false if invalid or expired
     */
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid or expired token
        }
    }

    /**
     * Extracts authentication details from a valid JWT token.
     * @param token the JWT token to extract authentication from
     * @return the Authentication object containing the user details
     */
    @Override
    public Authentication getAuthenticationFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        // Get expiration date from token
        Date expirationDate = claims.getExpiration();
        boolean isExpired = expirationDate.before(new Date());

        // Assuming the token has the subject (username) as the principal
//        UserDetails userDetails = new User(claims.getSubject(), token, new ArrayList<>());
        User user = new User();
        user.setUsername(claims.getSubject());
        user.setId(((Integer)claims.get("sid")).longValue());
        user.setAdmin(((Boolean) claims.get("isAdmin")));
        user.setExpired(isExpired);  // Set the expired flag
        UserDetails userDetails = user;
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    /**
     * Resolves the token from the Authorization header.
     * @param request the HttpServletRequest containing the token in the "Authorization" header
     * @return the JWT token or null if no token is found
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }

    /**
     * Authenticates the provided authentication request.
     * This method is called to verify the JWT and populate the security context.
     * @param authentication the authentication request object
     * @return the authenticated Authentication object if the token is valid
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();

        // Validate the token and get Authentication object from it
        if (validateToken(token)) {
            return getAuthenticationFromToken(token); // Return the authentication based on the token
        } else {
            throw new AuthenticationException("Invalid or expired token") {};
        }
    }

    /**
     * Indicates whether this provider supports the given Authentication type.
     * @param authentication the Authentication type to check
     * @return true if this provider supports the given authentication type
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * Converts an HTTP request to an Authentication object.
     * This method extracts the JWT token from the request header and creates the Authentication object.
     * @param request the HttpServletRequest containing the JWT token in the header
     * @return an Authentication object populated with user details from the JWT token
     */
    @Override
    public Authentication convert(HttpServletRequest request) {
        String token = resolveToken(request);
        if (token != null && validateToken(token)) {
            return getAuthenticationFromToken(token); // Return the authentication object from the token
        }
        return null;
    }
}
