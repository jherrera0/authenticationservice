package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.exception.UserEmailNotFoundException;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;

public class UserCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void createUser(User user) {
       if(userPersistencePort.getUserByEmail(user.getEmail()).equals(new User())){
           throw new UserEmailNotFoundException();
       }
       userPersistencePort.createUser(user);
    }
}
