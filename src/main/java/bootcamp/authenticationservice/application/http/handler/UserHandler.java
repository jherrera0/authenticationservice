package bootcamp.authenticationservice.application.http.handler;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.http.handler.inteface.IUserHandler;
import bootcamp.authenticationservice.application.http.mapper.ICreateUserRequestMapper;
import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.usecase.UserCase;
import bootcamp.authenticationservice.until.EntityConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final ICreateUserRequestMapper createUserRequestMapper;
    private final IUserServicePort userServicePort;


    @Override
    public void createUserWarehouse(CreateUserRequest request) {
        User user = createUserRequestMapper.ToUser(request);
        user.setRole(EntityConst.WAREHOUSE_ROLE);
        userServicePort.createUser(user);
    }
}
