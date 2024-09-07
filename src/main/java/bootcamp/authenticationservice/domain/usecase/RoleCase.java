package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IRoleServicePort;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;

public class RoleCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByName(String name) {
        return rolePersistencePort.getRoleByName(name);
    }
}
