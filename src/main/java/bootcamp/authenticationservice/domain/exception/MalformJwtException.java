package bootcamp.authenticationservice.domain.exception;

public class MalformJwtException extends RuntimeException {
    public MalformJwtException(String message) {
        super(message);
    }
}
