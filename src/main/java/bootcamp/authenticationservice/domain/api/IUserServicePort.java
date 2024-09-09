package bootcamp.authenticationservice.domain.api;

import bootcamp.authenticationservice.domain.model.User;

public interface IUserServicePort {
    void createUser(User user);
}
