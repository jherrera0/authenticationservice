package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserIllegalPhoneFormatException extends RuntimeException {
    public UserIllegalPhoneFormatException() {
        super(ExceptionConst.USER_PHONE_INVALID);
    }
}
