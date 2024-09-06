package bootcamp.authenticationservice.domain.spi;

import bootcamp.authenticationservice.domain.model.Role;

public interface IRolePersistencePort {
    Long getRoleIdByName(String name);
}
