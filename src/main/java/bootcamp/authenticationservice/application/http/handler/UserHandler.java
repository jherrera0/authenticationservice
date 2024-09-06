package bootcamp.authenticationservice.application.http.handler;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.http.handler.inteface.IUserHandler;
import bootcamp.authenticationservice.application.http.mapper.ICreateUserRequestMapper;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.GeneralMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final IUserRepository userRepository;
    private final ICreateUserRequestMapper createUserRequestMapper;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createUser(CreateUserRequest request) {
        User user = createUserRequestMapper.ToUser(request);
        GeneralMethods.validateUser(userRepository,request);
        UserEntity entity = GeneralMethods.createUser(roleRepository,passwordEncoder, user , EntityConst.WAREHOUSE_ROLE);
        userRepository.save(entity);
    }
}
