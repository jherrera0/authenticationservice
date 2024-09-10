package bootcamp.authenticationservice.domain.api;

public interface IAuthServicePort {
    String login(String email, String password);
}
