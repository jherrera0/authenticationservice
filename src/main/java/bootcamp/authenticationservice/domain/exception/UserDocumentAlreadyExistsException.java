package bootcamp.authenticationservice.domain.exception;

import bootcamp.authenticationservice.until.ExceptionConst;

public class UserDocumentAlreadyExistsException extends RuntimeException {
    public UserDocumentAlreadyExistsException() {
        super(ExceptionConst.USER_DOCUMENT_ALREADY_EXISTS);
    }
}
