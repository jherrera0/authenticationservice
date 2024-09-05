package bootcamp.authenticationservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    public RoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoleEntity() {

    }
}
