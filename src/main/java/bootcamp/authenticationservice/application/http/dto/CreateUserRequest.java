package bootcamp.authenticationservice.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String lastName;
    private String password;
    private String document;
    private String phone;
    private String email;
    private LocalDate birthDate;

}
