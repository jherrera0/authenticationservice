package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.domain.spi.IEncoderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncoderJpaAdapter implements IEncoderPersistencePort {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encoder(String password) {
        return passwordEncoder.encode(password);
    }
}
