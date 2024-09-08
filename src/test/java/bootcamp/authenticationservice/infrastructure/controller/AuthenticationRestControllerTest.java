package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.infrastructure.configuration.security.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthenticationRestControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationRestController authenticationRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ReturnsOkResponseWithAuthResponse_WhenCredentialsAreValid() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "password123");
        AuthResponse authResponse = new AuthResponse("valid.token.here");

        when(authenticationService.login(authRequest)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authenticationRestController.login(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authResponse, response.getBody());
    }


    @Test
    void login_ThrowsException_WhenAuthenticationServiceThrowsException() {
        AuthRequest authRequest = new AuthRequest("test@example.com", "wrongpassword");

        when(authenticationService.login(authRequest)).thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> authenticationRestController.login(authRequest));
    }


}