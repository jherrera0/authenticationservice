package bootcamp.authenticationservice.domain.spi;

public interface IEncoderPersistencePort {
    String encoder(String password);
}
