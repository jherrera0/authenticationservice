package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.AuthRequest;
import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import bootcamp.authenticationservice.application.http.handler.AuthHandler;
import bootcamp.authenticationservice.application.http.mapper.IAuthResponseMapper;
import bootcamp.authenticationservice.until.ConstantsRestController;
import bootcamp.authenticationservice.until.DocumentationConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController(ConstantsRestController.RUTE_AUTHENTICATION)
@RequiredArgsConstructor
@Tag(name = DocumentationConst.AUTH_CONTROLLER_NAME, description = DocumentationConst.AUTH_CONTROLLER_DESCRIPTION)
public class AuthenticationRestController {
    private final AuthHandler authHandler;
    private final IAuthResponseMapper authResponseMapper;
    @Operation(summary = DocumentationConst.AUTH_CONTROLLER_LOGIN_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_STATUS_201, description = DocumentationConst.DESCRIPTION_STATUS_201_AUTH, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_STATUS_403, description = DocumentationConst.DESCRIPTION_STATUS_403_AUTH, content = @Content)
    })
    @PreAuthorize(ConstantsRestController.PERMIT_ALL)
    @PostMapping(ConstantsRestController.RUTE_LOGIN)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse authResponse = authResponseMapper.toAuthResponse(authHandler.login(authRequest.getUsername(), authRequest.getPassword()));
        return ResponseEntity.ok(authResponse);
    }
}
