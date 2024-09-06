package bootcamp.authenticationservice.infrastructure.configuration.security;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.UserEmailNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;


    public AuthResponse login(@Valid AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);

        UserEntity user = userRepository.findByEmail(authRequest.getUsername()).orElseThrow(UserEmailNotFoundException::new);
        String token = jwtService.generateToken(user,generateExtraClaims(user));
        return new AuthResponse(token);
    }

    private Map<String,Object> generateExtraClaims(UserEntity user) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("Id",user.getId());
        extraClaims.put("Role",user.getRole().getName());
        return extraClaims;
    }
}
