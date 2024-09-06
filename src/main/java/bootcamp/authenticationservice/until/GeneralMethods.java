package bootcamp.authenticationservice.until;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        user.setRole(roleRepository.findByName(role).get());
        return user;
    }

    public static void validateUser(CreateUserRequest createUserRequest) {
        if (createUserRequest.getName() == null || createUserRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (createUserRequest.getLastName() == null || createUserRequest.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (createUserRequest.getDocument() == null || createUserRequest.getDocument().isEmpty()) {
            throw new IllegalArgumentException("Document is required");
        }
        if (createUserRequest.getPhone() == null || createUserRequest.getPhone().isEmpty()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (createUserRequest.getBirthDate() == null) {
            throw new IllegalArgumentException("Birth date is required");
        }
        if (createUserRequest.getPassword() == null || createUserRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (createUserRequest.getEmail() == null || createUserRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
    }

    private void validatePhone(String phone) {
        String phoneRegex = "^[+]?[0-9]{1,13}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (!pattern.matcher(phone).matches()) {
            throw new IllegalArgumentException("El número de teléfono no es válido.");
        }
    }

    private void validateDocument(String document) {
        if (!document.matches("[0-9]+")) {
            throw new IllegalArgumentException("El documento de identidad debe ser numérico.");
        }
    }
    private GeneralMethods(){
    }
}
