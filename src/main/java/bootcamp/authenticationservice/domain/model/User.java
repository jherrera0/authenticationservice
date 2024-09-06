package bootcamp.authenticationservice.domain.model;

import java.time.LocalDate;

public class User {
    private String password;
    private String email;
    private String name;
    private String document;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String role;

    public User() {
    }

    public User( String password, String email, String name, String document, String lastName, LocalDate birthDate, String phone, String role) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.document = document;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.role = role;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}