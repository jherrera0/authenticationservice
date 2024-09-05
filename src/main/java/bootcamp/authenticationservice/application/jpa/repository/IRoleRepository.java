package bootcamp.authenticationservice.application.jpa.repository;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long>{
Optional<RoleEntity> findByName(String name);
}
