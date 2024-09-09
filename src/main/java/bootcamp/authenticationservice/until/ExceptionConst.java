package bootcamp.authenticationservice.until;

import java.time.LocalDate;

public class ExceptionConst {
    public static final String USER_PHONE_INVALID = "Phone number is invalid, must be 13 digits";
    public static final String USER_DOCUMENT_ALREADY_EXISTS = "User with this document already exists";
    public static final String USER_BIRTH_DATE_INVALID = "User is under legal age";
    public static final String USER_EMAIL_ALREADY_EXISTS = "User with this email already exists";
    public static final String USER_BIRTH_DATE_EMPTY = "User Birth date is empty or null";
    public static final String USER_PHONE_REGEX = "^[+]?[0-9]{1,12}$";
    public static final String EMPTY = "";
    public static final String ROLE_EMPTY = "Role name cannot be empty";
    public static final LocalDate BIRTH_DATE_EMPTY = LocalDate.of(0, 1, 1);
    public static final Integer INVALID_AGE = 17;
    public static final String USER_INVALID_PHONE = "invalid phone number";
    public static final String BAD_CREDENTIALS = "Bad credentials";

    private ExceptionConst() {
    }
}
