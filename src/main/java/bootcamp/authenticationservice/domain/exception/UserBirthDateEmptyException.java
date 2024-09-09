package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserBirthDateEmptyException extends RuntimeException {
    public UserBirthDateEmptyException() {
        super(ExceptionConst.USER_BIRTH_DATE_EMPTY);
    }
}
