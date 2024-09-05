package bootcamp.authenticationservice.infrastructure.configuration.security;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.application.http.dto.RegisterRequest;
import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.until.EntityConst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(@Valid AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);

        UserEntity user = userRepository.findByEmail(authRequest.getUsername()).get();

        String token = jwtService.generateToken(user,generateExtraClaims(user));
        return new AuthResponse(token);
    }

    public AuthResponse register(@Valid RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setDocument(registerRequest.getDocument());
        user.setPhone(registerRequest.getPhone());
        user.setBirthDate(registerRequest.getBirthDate());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(roleRepository.findByName(EntityConst.ADMIN_ROLE).get());
        userRepository.save(user);

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
