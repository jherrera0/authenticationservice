package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.mapper.IRoleEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;

public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;

    public RoleJpaAdapter(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Long getRoleIdByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(name).orElseThrow();
        return roleEntity.getId();
    }
}
