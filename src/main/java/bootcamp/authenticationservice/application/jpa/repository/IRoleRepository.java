package bootcamp.authenticationservice.application.jpa.repository;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<RoleEntity, Long>{
    RoleEntity findByName(String name);
}
