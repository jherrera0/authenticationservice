package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.until.ConstantsRestController;
import bootcamp.authenticationservice.until.DocumentationConst;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.GeneralMethods;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(ConstantsRestController.RUTE_USER)
@RequiredArgsConstructor
@Tag(name = DocumentationConst.USER_CONTROLLER_NAME, description = DocumentationConst.USER_CONTROLLER_DESCRIPTION)
public class UserRestController {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = DocumentationConst.USER_CONTROLLER_CREATE_WAREHOUSE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConst.CODE_STATUS_201, description = DocumentationConst.DESCRIPTION_STATUS_201_USER, content = @Content),
            @ApiResponse(responseCode = DocumentationConst.CODE_STATUS_403, description = DocumentationConst.DESCRIPTION_STATUS_403_USER, content = @Content)
    })
    @PostMapping(ConstantsRestController.RUTE_CREATE_AUX_WAREHOUSE)
    @PreAuthorize(ConstantsRestController.HAS_ROLE_ADMIN)
    public void createAssWarehouse(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserEntity user = GeneralMethods.createUser(roleRepository,passwordEncoder, createUserRequest,EntityConst.WAREHOUSE_ROLE);
        userRepository.save(user);

    }

}

