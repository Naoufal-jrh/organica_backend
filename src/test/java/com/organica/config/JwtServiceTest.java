package com.organica.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        userDetails = mock(UserDetails.class);
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String username = "testUser";
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(jwtService.getSingInKey(), SignatureAlgorithm.HS256)
                .compact();

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    void isTokenValid_shouldReturnTrueForValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtService.generateToken(userDetails);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void generateToken_shouldGenerateValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUsername(token);

        assertNotNull(token);
        assertEquals("testUser", username);
    }

    @Test
    void extractClaim_shouldReturnCorrectClaim() {
        String username = "testUser";
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(jwtService.getSingInKey(), SignatureAlgorithm.HS256)
                .compact();

        String extractedSubject = jwtService.extractClaim(token, Claims::getSubject);

        assertEquals(username, extractedSubject);
    }

    @Test
    void extractExpiration_shouldReturnCorrectExpirationDate() {
        long expirationTime = System.currentTimeMillis() + 1000 * 60 * 24; // 24 minutes
        Date expirationDate = new Date(expirationTime);
        String token = Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(jwtService.getSingInKey(), SignatureAlgorithm.HS256)
                .compact();

        Date extractedExpiration = jwtService.extractClaim(token, Claims::getExpiration);

        assertEquals(expirationDate.getTime() / 1000, extractedExpiration.getTime() / 1000,
                "The extracted expiration date differs from the expected date at the second level.");
    }


}
