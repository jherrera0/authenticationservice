package bootcamp.authenticationservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@Entity
public class RoleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    public RoleEntity() {
        // Default constructor empty
    }

    public RoleEntity(Long id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }
}
