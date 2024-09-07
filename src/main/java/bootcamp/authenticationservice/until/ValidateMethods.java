package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.domain.exception.UserIllegalPhoneFormatException;
import bootcamp.authenticationservice.domain.exception.UserUnderAgeException;
import bootcamp.authenticationservice.domain.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidateMethods {
    public static void validateUser( User createUserRequest) {
        validAge(createUserRequest.getBirthDate());
        validatePhone(createUserRequest.getPhone());
    }
    private static void validAge(LocalDate birthDate) {
        if(birthDate.plusYears(18).isAfter(LocalDate.now())){
            throw new UserUnderAgeException();
        }
    }


    private static void validatePhone(String phone) {
        Pattern pattern = Pattern.compile(ExceptionConst.USER_PHONE_REGEX);
        if (!pattern.matcher(phone).matches()) {
            throw new UserIllegalPhoneFormatException();
        }
    }

    private ValidateMethods(){
    }

}
