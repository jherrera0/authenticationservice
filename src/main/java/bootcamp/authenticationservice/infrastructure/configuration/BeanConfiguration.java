package bootcamp.authenticationservice.infrastructure.configuration;

import bootcamp.authenticationservice.application.jpa.adapter.RoleJpaAdapter;
import bootcamp.authenticationservice.application.jpa.adapter.UserJpaAdapter;
import bootcamp.authenticationservice.application.jpa.mapper.IRoleEntityMapper;
import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.api.IRoleServicePort;
import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.domain.usecase.RoleCase;
import bootcamp.authenticationservice.domain.usecase.UserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleCase(rolePersistencePort());
    }

}
