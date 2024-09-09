package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserUnderAgeException extends RuntimeException {
    public UserUnderAgeException() {
        super(ExceptionConst.USER_BIRTH_DATE_INVALID);
    }
}
