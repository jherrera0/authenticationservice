package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.infrastructure.configuration.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse authResponse = authenticationService.login(authRequest);
        return ResponseEntity.ok(authResponse);
    }

}
