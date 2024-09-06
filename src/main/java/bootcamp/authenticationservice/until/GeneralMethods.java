package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.UserDocumentAlreadyExistsException;
import bootcamp.authenticationservice.domain.exception.UserIllegalPhoneFormatException;
import bootcamp.authenticationservice.domain.exception.UserUnderAgeException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class GeneralMethods {

    public static UserEntity createUser(IRoleRepository roleRepository, PasswordEncoder passwordEncoder, CreateUserRequest createUserRequest, String role) {
        UserEntity user = new UserEntity();
        user.setName(createUserRequest.getName());
        user.setLastName(createUserRequest.getLastName());
        user.setDocument(createUserRequest.getDocument());
        user.setPhone(createUserRequest.getPhone());
        user.setBirthDate(createUserRequest.getBirthDate());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setRole(roleRepository.findByName(role).orElseThrow());
        return user;
    }

    public static void validateUser(IUserRepository userRepository,CreateUserRequest createUserRequest) {
        validateDocument(userRepository,createUserRequest.getDocument());
        validAge(createUserRequest.getBirthDate());
        validatePhone(createUserRequest.getPhone());
    }

    private static void validateDocument(IUserRepository userRepository, String document) {
        if(userRepository.findByDocument(document).isPresent()){
            throw new UserDocumentAlreadyExistsException();
        }
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
    private GeneralMethods(){
    }
}
