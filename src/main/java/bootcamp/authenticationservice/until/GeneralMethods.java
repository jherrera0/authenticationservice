package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        user.setRole(roleRepository.findByName(role).get());
        return user;
    }
    private GeneralMethods(){
    }
}
