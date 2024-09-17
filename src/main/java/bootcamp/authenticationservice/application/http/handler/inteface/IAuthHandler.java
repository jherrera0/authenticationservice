package bootcamp.authenticationservice.application.http.handler.inteface;

public interface IAuthHandler {
    String login(String email, String password);
}
