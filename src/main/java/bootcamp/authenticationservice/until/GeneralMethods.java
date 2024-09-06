package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.exception.UserDocumentAlreadyExistsException;
import bootcamp.authenticationservice.domain.exception.UserEmailAlreadyExistException;
import bootcamp.authenticationservice.domain.exception.UserIllegalPhoneFormatException;
import bootcamp.authenticationservice.domain.exception.UserUnderAgeException;
import bootcamp.authenticationservice.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class GeneralMethods {

    public static UserEntity createUser(IRoleRepository roleRepository, PasswordEncoder passwordEncoder, User createUserRequest, String role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setLastName(createUserRequest.getLastName());
        userEntity.setDocument(createUserRequest.getDocument());
        userEntity.setPhone(createUserRequest.getPhone());
        userEntity.setBirthDate(createUserRequest.getBirthDate());
        userEntity.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setRole(roleRepository.findByName(role).orElseThrow());
        return userEntity;
    }

    public static void validateUser(IUserRepository userRepository,CreateUserRequest createUserRequest) {
        validateDocument(userRepository,createUserRequest.getDocument());
        validAge(createUserRequest.getBirthDate());
        validatePhone(createUserRequest.getPhone());
        validateEmail(userRepository,createUserRequest.getEmail());
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

    private static void validateEmail(IUserRepository userRepository,String email) {
        if(userRepository.findByEmail(email).isPresent()){
            throw new UserEmailAlreadyExistException();
        }

    }


    private GeneralMethods(){
    }
}
