package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException() {
        super(ExceptionConst.USER_EMAIL_NOT_FOUND);
    }
}
