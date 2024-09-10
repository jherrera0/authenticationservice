package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IAuthServicePort;
import bootcamp.authenticationservice.domain.spi.IAuthPersistencePort;
import bootcamp.authenticationservice.until.ExceptionConst;
import org.springframework.security.authentication.BadCredentialsException;

public class AuthCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;

    public AuthCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        if (authPersistencePort.validateCredentials(email, password)) {
            return authPersistencePort.generateToken(authPersistencePort.authenticate(email, password));
        } else {
            throw new BadCredentialsException(ExceptionConst.BAD_CREDENTIALS);
        }
    }
}
