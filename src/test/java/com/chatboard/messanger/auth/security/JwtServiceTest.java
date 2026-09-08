package com.chatboard.messanger.auth.security;

import com.chatboard.messanger.auth.config.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        JwtProperties properties = new JwtProperties();
        ReflectionTestUtils.setField(properties, "secret",
                "ZnVja2luZy1zZWNyZXQta2V5LWZvci10ZXN0aW5nLXB1cnBvc2VzLW9ubHk=");
        ReflectionTestUtils.setField(properties, "accessExpirationMs", 900000L);
        ReflectionTestUtils.setField(properties, "refreshExpirationMs", 604800000L);
        jwtService = new JwtService(properties);
    }

    @Test
    void generateAccessToken_returnsValidToken() {
        String token = jwtService.generateAccessToken("testuser");
        assertNotNull(token);
        assertEquals("testuser", jwtService.extractUsername(token));
    }

    @Test
    void generateRefreshToken_returnsValidToken() {
        String token = jwtService.generateRefreshToken("testuser");
        assertNotNull(token);
        assertEquals("testuser", jwtService.extractUsername(token));
    }

    @Test
    void validateToken_returnsTrueForValidToken() {
        String token = jwtService.generateAccessToken("testuser");
        assertTrue(jwtService.validateToken(token));
    }

    @Test
    void validateToken_returnsFalseForInvalidToken() {
        assertFalse(jwtService.validateToken("invalid.token.here"));
    }

    @Test
    void extractUsername_returnsCorrectUsername() {
        String token = jwtService.generateAccessToken("alice");
        assertEquals("alice", jwtService.extractUsername(token));
    }
}
