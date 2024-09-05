package bootcamp.authenticationservice.infrastructure.controller;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.until.ConstantsRestController;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.GeneralMethods;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/createAssWarehouse")
    @PreAuthorize(ConstantsRestController.HAS_ROLE_ADMIN)
    public void createAssWarehouse(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserEntity user = GeneralMethods.createUser(roleRepository,passwordEncoder, createUserRequest,EntityConst.WAREHOUSE_ROLE);
        userRepository.save(user);

    }

}

