package bootcamp.authenticationservice.until;

public class ExceptionConst {
    public static final String USER_EMAIL_NOT_FOUND = "User with this email not found";
    public static final String USER_PHONE_INVALID = "Phone number is invalid, must be 13 digits";
    public static final String USER_DOCUMENT_ALREADY_EXISTS = "User with this document already exists";
    public static final String USER_BIRTH_DATE_INVALID = "User is under legal age";

    public static final Integer USER_PHONE_MAX_LENGTH = 13;
    public static final Integer USER_PHONE_MIN_LENGTH = 10;

    private ExceptionConst() {
    }
}
