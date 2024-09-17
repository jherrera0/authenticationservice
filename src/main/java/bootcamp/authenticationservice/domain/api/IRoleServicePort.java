package bootcamp.authenticationservice.domain.api;

import bootcamp.authenticationservice.domain.model.Role;

public interface IRoleServicePort {
    Role getRoleByName(String name);
}
