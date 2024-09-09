package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class RoleEmptyException extends RuntimeException {
    public RoleEmptyException() {
        super(ExceptionConst.ROLE_EMPTY);
    }
}
