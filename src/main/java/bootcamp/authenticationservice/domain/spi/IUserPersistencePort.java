package bootcamp.authenticationservice.domain.spi;

import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.model.User;


public interface IUserPersistencePort {
    void createUserWarehouse(User user, Role role);
    User getUserByEmail(String email);
    User getUserByDocument(String document);
    UserEntity getUserEntityByEmail(String email);
}
