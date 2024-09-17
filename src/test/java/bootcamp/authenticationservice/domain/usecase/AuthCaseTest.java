package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.exception.BadCredentialException;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IAuthPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthCaseTest {
    @Mock
    private IAuthPersistencePort authPersistencePort;

    private AuthCase authCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authCase = new AuthCase(authPersistencePort);
    }
    
    @Test
    void login_ThrowsBadCredentialsException_WhenCredentialsAreInvalid() {
        String email = "invalid@example.com";
        String password = "invalidPassword";

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(false);

        assertThrows(BadCredentialException.class, () -> authCase.login(email, password));
    }

    @Test
    void login_ThrowsBadCredentialsException_WhenEmailIsNull() {
        String password = "validPassword";

        when(authPersistencePort.validateCredentials(null, password)).thenReturn(false);

        assertThrows(BadCredentialException.class, () -> authCase.login(null, password));
    }

    @Test
    void login_ThrowsBadCredentialsException_WhenPasswordIsNull() {
        String email = "valid@example.com";

        when(authPersistencePort.validateCredentials(email, null)).thenReturn(false);

        assertThrows(BadCredentialException.class, () -> authCase.login(email, null));
    }
    @Test
    void login_ReturnsToken_WhenCredentialsAreValid() {
        String email = "valid@example.com";
        String password = "validPassword";
        String expectedToken = "validToken";

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(true);
        when(authPersistencePort.authenticate(email, password)).thenReturn(new User());
        when(authPersistencePort.generateToken(any(User.class))).thenReturn(expectedToken);

        String token = authCase.login(email, password);

        assertNotNull(token);
        assertEquals(expectedToken, token);
    }

}