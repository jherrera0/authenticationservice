package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.domain.exception.UserBirthDateEmptyException;
import bootcamp.authenticationservice.domain.exception.UserIllegalPhoneFormatException;
import bootcamp.authenticationservice.domain.exception.UserUnderAgeException;
import bootcamp.authenticationservice.domain.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateMethodsTest {

    @Test
    void validateUser_withValidData_doesNotThrowException() {
        User user = new User();
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        user.setPhone(TestConsts.USER_VALID_PHONE);

        ValidateMethods.validateUser(user);
    }

    @Test
    void validateUser_withNullBirthDate_throwsUserBirthDateEmptyException() {
        User user = new User();
        user.setBirthDate(null);
        user.setPhone(TestConsts.USER_VALID_PHONE);

        assertThrows(UserBirthDateEmptyException.class, () -> ValidateMethods.validateUser(user));
    }

    @Test
    void validateUser_withUnderageBirthDate_throwsUserUnderAgeException() {
        User user = new User();
        user.setBirthDate(LocalDate.now().minusYears(ExceptionConst.INVALID_AGE));
        user.setPhone(TestConsts.USER_VALID_PHONE);

        assertThrows(UserUnderAgeException.class, () -> ValidateMethods.validateUser(user));
    }

    @Test
    void validateUser_withInvalidPhone_throwsUserIllegalPhoneFormatException() {
        User user = new User();
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        user.setPhone(ExceptionConst.USER_INVALID_PHONE);

        assertThrows(UserIllegalPhoneFormatException.class, () -> ValidateMethods.validateUser(user));
    }

    @Test
    void validateUser_withEmptyBirthDateConstant_throwsUserBirthDateEmptyException() {
        User user = new User();
        user.setBirthDate(ExceptionConst.BIRTH_DATE_EMPTY);
        user.setPhone(TestConsts.USER_VALID_PHONE);

        assertThrows(UserBirthDateEmptyException.class, () -> ValidateMethods.validateUser(user));
    }

    @Test
    void validateUser_withValidPhoneFormat_doesNotThrowException() {
        User user = new User();
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        user.setPhone(TestConsts.USER_VALID_PHONE);

        ValidateMethods.validateUser(user);
    }

    @Test
    void validateUser_withPhoneContainingLetters_throwsUserIllegalPhoneFormatException() {
        User user = new User();
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        user.setPhone("123-abc-7890");

        assertThrows(UserIllegalPhoneFormatException.class, () -> ValidateMethods.validateUser(user));
    }

    @Test
    void validateUser_withPhoneContainingSpecialCharacters_throwsUserIllegalPhoneFormatException() {
        User user = new User();
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        user.setPhone("123456789@");

        assertThrows(UserIllegalPhoneFormatException.class, () -> ValidateMethods.validateUser(user));
    }
}