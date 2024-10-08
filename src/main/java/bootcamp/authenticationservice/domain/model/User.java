package bootcamp.authenticationservice.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
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

    public User(String name, String lastName, String email, String password, String document, LocalDate birthDate, String phone, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.document = document;
        this.birthDate = birthDate;
        this.phone = phone;
        this.role = role;
    }


    public User(Long id, String name, String lastName, String email, String password, String document, LocalDate birthDate, String phone, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.document = document;
        this.birthDate = birthDate;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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