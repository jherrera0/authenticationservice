package bootcamp.authenticationservice.domain.spi;

import bootcamp.authenticationservice.domain.model.User;


public interface IUserPersistencePort {
    void createUser(User user);
    User getUserByEmail(String email);
}
