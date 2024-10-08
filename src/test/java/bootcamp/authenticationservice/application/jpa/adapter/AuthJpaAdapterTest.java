package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.domain.exception.MalformJwtException;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.infrastructure.configuration.exceptiohandler.GlobalExceptionHandler;
import bootcamp.authenticationservice.infrastructure.configuration.security.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthJpaAdapterTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthJpaAdapter authJpaAdapter;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private GlobalExceptionHandler globalExceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authJpaAdapter = new AuthJpaAdapter(authenticationManager,jwtService,userPersistencePort);
    }
    @Test
    void authenticateValidUser() {
        String email = "test@example.com";
        String password = "password";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail(email);
        userEntity.setRole(new RoleEntity());
        userEntity.getRole().setName("USER");
        userEntity.getRole().setId(1L);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .thenReturn(authentication);

        User user = authJpaAdapter.authenticate(email, password);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals("USER", user.getRole());
    }

    @Test
    void authenticateInvalidUser() {
        String email = "invalid@example.com";
        String password = "wrongpassword";

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> authJpaAdapter.authenticate(email, password));
    }
    @Test
    void generateTokenForNullUser() {
        String token = authJpaAdapter.generateToken(null);

        assertNull(token);
    }

    @Test
    void validateCredentialsValid() {
        String email = "test@example.com";
        String password = "password";

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .thenReturn(mock(Authentication.class));

        boolean isValid = authJpaAdapter.validateCredentials(email, password);

        assertTrue(isValid);
    }

    @Test
    void validateCredentialsInvalid() {
        String email = "invalid@example.com";
        String password = "wrongpassword";

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        boolean isValid = authJpaAdapter.validateCredentials(email, password);

        assertFalse(isValid);
    }

    @Test
    void extractAllClaims_ReturnsClaims_WhenJwtIsValid() {
        String validJwt = "valid.jwt.token";
        Claims claims = mock(Claims.class);
        when(jwtService.extractAllClaims(validJwt)).thenReturn(claims);

        Claims result = jwtService.extractAllClaims(validJwt);

        assertNotNull(result);
        assertEquals(claims, result);
    }

    @Test
    void extractAllClaims_ThrowsMalformJwtException_WhenJwtIsInvalid() {
        String invalidJwt = "invalid.jwt.token";
        when(jwtService.extractAllClaims(invalidJwt)).thenThrow(new MalformJwtException("Invalid JWT"));

        assertThrows(MalformJwtException.class, () -> jwtService.extractAllClaims(invalidJwt));
    }

}