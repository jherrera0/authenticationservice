package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IAuthPersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.infrastructure.configuration.security.JwtService;
import bootcamp.authenticationservice.until.JwtConst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUserPersistencePort userPersistencePort;


    @Override
    public User authenticate(String email, String password) {
        Authentication authUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        UserEntity userEntity = (UserEntity) authUser.getPrincipal();
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole().getName());


        return user;
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(user, generateExtraClaims(user));
    }

    @Override
    public boolean validateCredentials(String userEmail, String userPassword) {
        if(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEmail,
                        userPassword
                )
        ).isAuthenticated()){
            return true;
        }else{
            return false;
        }
    }
    private Map<String,Object> generateExtraClaims(User user) {
        UserEntity userEntity = userPersistencePort.getUserEntityByEmail(user.getEmail());
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put(JwtConst.ID,userEntity.getId());
        extraClaims.put(JwtConst.ROLE,userEntity.getRole().getName());
        return extraClaims;
    }
}
