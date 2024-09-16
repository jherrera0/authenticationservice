package bootcamp.authenticationservice.infrastructure.configuration.security;

import bootcamp.authenticationservice.domain.model.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {
    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }
    @Test
    void generateToken_ReturnsValidToken_ForValidUser() {
        User user = new User();
        user.setEmail("test@example.com");
        Map<String, Object> extraClaims = Map.of("role", "USER");

        String token = jwtService.generateToken(user, extraClaims);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void generateToken_ThrowsException_ForNullUser() {
        Map<String, Object> extraClaims = Map.of("role", "USER");

        assertThrows(NullPointerException.class, () -> jwtService.generateToken(null, extraClaims));
    }

    @Test
    void extractUsername_ReturnsCorrectUsername_ForValidToken() {
        User user = new User();
        user.setEmail("test@example.com");
        String token = jwtService.generateToken(user, Map.of());

        String username = jwtService.extractUsername(token);

        assertEquals("test@example.com", username);
    }


    @Test
    void extractAllClaims_ReturnsClaims_ForValidToken() {
        User user = new User();
        user.setEmail("test@example.com");
        String token = jwtService.generateToken(user, Map.of("role", "USER"));

        Claims claims = jwtService.extractAllClaims(token);

        assertEquals("test@example.com", claims.getSubject());
        assertEquals("USER", claims.get("role"));
    }
}