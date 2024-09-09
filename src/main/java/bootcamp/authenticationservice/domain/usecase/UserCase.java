package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.exception.UserDocumentAlreadyExistsException;
import bootcamp.authenticationservice.domain.exception.UserEmailAlreadyExistException;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.until.ValidateMethods;

public class UserCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public UserCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }


    @Override
    public void createUser(User user) {
       if(userPersistencePort.getUserByDocument(user.getDocument()) != null){
           throw new UserDocumentAlreadyExistsException();
       }
       if(userPersistencePort.getUserByEmail(user.getEmail())!= null){
           throw new UserEmailAlreadyExistException();
       }
       ValidateMethods.validateUser(user);
       Role role = rolePersistencePort.getRoleByName(user.getRole());
       userPersistencePort.createUserWarehouse(user,role);
    }
}
