package bootcamp.authenticationservice.domain.spi;

import bootcamp.authenticationservice.domain.model.Role;

public interface IRolePersistencePort {
    Role getRoleByName(String name);
}
