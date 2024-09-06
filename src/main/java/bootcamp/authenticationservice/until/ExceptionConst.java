package bootcamp.authenticationservice.until;

public class ExceptionConst {
    public static final String USER_EMAIL_NOT_FOUND = "User with this email not found";
    public static final String USER_PHONE_INVALID = "Phone number is invalid, must be 13 digits";
    public static final String USER_DOCUMENT_ALREADY_EXISTS = "User with this document already exists";
    public static final String USER_BIRTH_DATE_INVALID = "User is under legal age";

    public static final String USER_PHONE_REGEX = "^[+]?[0-9]{1,12}$";
    private ExceptionConst() {
    }
}
