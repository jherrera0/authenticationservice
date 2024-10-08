package bootcamp.authenticationservice.application.jpa.repository;

import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByDocument(String document);
}
