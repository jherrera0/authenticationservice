package bootcamp.authenticationservice.infrastructure.configuration;

import bootcamp.authenticationservice.application.jpa.adapter.AuthJpaAdapter;
import bootcamp.authenticationservice.application.jpa.adapter.EncoderJpaAdapter;
import bootcamp.authenticationservice.application.jpa.adapter.RoleJpaAdapter;
import bootcamp.authenticationservice.application.jpa.adapter.UserJpaAdapter;
import bootcamp.authenticationservice.application.jpa.mapper.IRoleEntityMapper;
import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.api.IAuthServicePort;
import bootcamp.authenticationservice.domain.api.IRoleServicePort;
import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.spi.IAuthPersistencePort;
import bootcamp.authenticationservice.domain.spi.IEncoderPersistencePort;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.domain.usecase.AuthCase;
import bootcamp.authenticationservice.domain.usecase.RoleCase;
import bootcamp.authenticationservice.domain.usecase.UserCase;
import bootcamp.authenticationservice.infrastructure.configuration.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, roleEntityMapper, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IEncoderPersistencePort encoderPersistencePort(){
        return new EncoderJpaAdapter(passwordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserCase(userPersistencePort(), rolePersistencePort(), encoderPersistencePort());
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleCase(rolePersistencePort());
    }

    @Bean
    public IAuthServicePort authServicePort(){
        return new AuthCase(authPersistencePort());
    }

    @Bean
    IAuthPersistencePort authPersistencePort(){
        return new AuthJpaAdapter(authenticationManager, jwtService, userPersistencePort());
    }
}