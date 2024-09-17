package bootcamp.authenticationservice.application.http.handler;

import bootcamp.authenticationservice.application.http.handler.inteface.IAuthHandler;
import bootcamp.authenticationservice.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;
    @Override
    public String login(String email, String password) {
        return authServicePort.login(email, password);
    }
}
