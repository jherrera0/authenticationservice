package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.application.http.handler.AuthHandler;
import bootcamp.authenticationservice.application.http.mapper.IAuthResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AuthenticationRestControllerTest {
    @Mock
    private AuthHandler authHandler;

    @Mock
    private IAuthResponseMapper authResponseMapper;

    @InjectMocks
    private AuthenticationRestController authenticationRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ReturnsOkResponseWithAuthResponse_WhenCredentialsAreValid() {
        AuthRequest authRequest = new AuthRequest("validUser", "validPassword");
        AuthResponse authResponse = new AuthResponse("valid.token.here");

        when(authHandler.login(authRequest.getUsername(), authRequest.getPassword())).thenReturn(authResponse.getToken());
        when(authResponseMapper.toAuthResponse(authResponse.getToken())).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authenticationRestController.login(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authResponse, response.getBody());
    }

    @Test
    void login_ThrowsException_WhenAuthHandlerThrowsException() {
        AuthRequest authRequest = new AuthRequest("validUser", "wrongPassword");

        when(authHandler.login(authRequest.getUsername(), authRequest.getPassword())).thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> authenticationRestController.login(authRequest));
    }

    @Test
    void login_ThrowsException_WhenAuthRequestIsNull() {
        AuthRequest authRequest = null;

        assertThrows(NullPointerException.class, () -> authenticationRestController.login(authRequest));
    }

    @Test
    void login_ReturnsForbiddenResponse_WhenUserIsNotAuthorized() {
        AuthRequest authRequest = new AuthRequest("unauthorizedUser", "validPassword");

        when(authHandler.login(authRequest.getUsername(), authRequest.getPassword())).thenThrow(new AccessDeniedException("Access denied"));

        assertThrows(AccessDeniedException.class, () -> authenticationRestController.login(authRequest));
    }
}