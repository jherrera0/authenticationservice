package bootcamp.authenticationservice.application.http.handler.inteface;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;

public interface IUserHandler {
    void createUserWarehouse(CreateUserRequest request);
    void registerCustomer(CreateUserRequest request);
}
