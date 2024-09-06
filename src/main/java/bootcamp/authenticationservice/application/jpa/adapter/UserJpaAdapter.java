package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.UserEmailNotFoundException;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper IUserEntityMapper;

    @Override
    public void createUser(User user) {
        userRepository.save(IUserEntityMapper.toEntity(user));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(IUserEntityMapper::toDomain).orElseThrow(UserEmailNotFoundException::new);
    }
}
