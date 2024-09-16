package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.api.IAuthServicePort;
import bootcamp.authenticationservice.domain.exception.BadCredentialException;
import bootcamp.authenticationservice.domain.spi.IAuthPersistencePort;
import bootcamp.authenticationservice.until.ExceptionConst;

public class AuthCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;

    public AuthCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        if (authPersistencePort.validateCredentials(email, password)) {
            String Token = authPersistencePort.generateToken(authPersistencePort.authenticate(email, password));
            return Token;
        } else {
            throw new BadCredentialException(ExceptionConst.BAD_CREDENTIALS);
        }
    }
}
