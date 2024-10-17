package org.mostafa.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreatedAt: 9/10/2023
 */
@Component
public class JwtProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expiration}")
    private long validityInMilliseconds;

    /**
     * Create JWT string given username and roles.
     *
     * @param username
     * @return jwt string
     */
    public String createToken(String username) {
        //Add the username to the payload
        Map<String, Object> claims = new HashMap<>();
        //Build the Token
        Date now = new Date();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + validityInMilliseconds))
                .and()
                .signWith(this.getSignKey())
                .compact();
    }

    /**
     * Validate the JWT String
     *
     * @param token JWT string
     * @return true if valid, false otherwise
     */
    public boolean isValidToken(String token) {
        Jwts.parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token);
        return true;
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSignKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        return this.extractClaims(token).getSubject();
    }

    private Date extractIssueDateTime(String token) {
        return this.extractClaims(token).getIssuedAt();
    }

    private Date extractExpireDateTime(String token) {
        return this.extractClaims(token).getExpiration();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}