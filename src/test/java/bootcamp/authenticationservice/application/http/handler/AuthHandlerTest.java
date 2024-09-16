package bootcamp.authenticationservice.application.http.handler;

import bootcamp.authenticationservice.domain.api.IAuthServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthHandlerTest {
    @Mock
    private IAuthServicePort authServicePort;

    @Mock
    private AuthHandler authHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authHandler = new AuthHandler(authServicePort);
    }
    @Test
    void loginWithValidCredentials() {
        String email = "test@example.com";
        String password = "password";
        String expectedToken = "validToken";

        when(authServicePort.login(email, password)).thenReturn(expectedToken);

        String token = authHandler.login(email, password);

        assertNotNull(token);
        assertEquals(expectedToken, token);
    }

    @Test
    void loginWithInvalidCredentials() {
        String email = "invalid@example.com";
        String password = "wrongpassword";

        when(authServicePort.login(email, password)).thenReturn(null);

        String token = authHandler.login(email, password);

        assertNull(token);
    }

    @Test
    void loginWithEmptyEmail() {
        String email = "";
        String password = "password";

        when(authServicePort.login(email, password)).thenReturn(null);

        String token = authHandler.login(email, password);

        assertNull(token);
    }

    @Test
    void loginWithEmptyPassword() {
        String email = "test@example.com";
        String password = "";

        when(authServicePort.login(email, password)).thenReturn(null);

        String token = authHandler.login(email, password);

        assertNull(token);
    }

    @Test
    void loginWithNullEmail() {
        String email = null;
        String password = "password";

        when(authServicePort.login(email, password)).thenReturn(null);

        String token = authHandler.login(email, password);

        assertNull(token);
    }

    @Test
    void loginWithNullPassword() {
        String email = "test@example.com";
        String password = null;

        when(authServicePort.login(email, password)).thenReturn(null);

        String token = authHandler.login(email, password);

        assertNull(token);
    }
}