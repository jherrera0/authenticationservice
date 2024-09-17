package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IRoleServicePort;
import bootcamp.authenticationservice.domain.exception.RoleEmptyException;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.until.ExceptionConst;

public class RoleCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByName(String name) {
        if (name == null || name.equals(ExceptionConst.EMPTY)) {
            throw new RoleEmptyException();
        }
        return rolePersistencePort.getRoleByName(name);
    }
}
