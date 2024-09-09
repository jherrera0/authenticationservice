package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserEmailAlreadyExistException extends  RuntimeException {
    public UserEmailAlreadyExistException() {
        super(ExceptionConst.USER_EMAIL_ALREADY_EXISTS);
    }
}
