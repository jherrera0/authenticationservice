package bootcamp.authenticationservice.until;

public class UserDocumentAlreadyExistsException extends RuntimeException {
    public UserDocumentAlreadyExistsException() {
        super(ExceptionConst.USER_DOCUMENT_ALREADY_EXISTS);
    }
}
