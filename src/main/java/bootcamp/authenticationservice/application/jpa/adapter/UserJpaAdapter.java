package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.UserEmailNotFoundException;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void createUserWarehouse(User user, Role role) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        RoleEntity roleEntity = roleRepository.findByName(role.getName());
        userEntity.setRole(roleEntity);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toDomain(userRepository.findByEmail(email));
    }

    @Override
    public User getUserByDocument(String document) {
        return userEntityMapper.toDomain(userRepository.findByDocument(document));
    }
}
