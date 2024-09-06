package bootcamp.authenticationservice.domain.spi;

public interface IRolePersistencePort {
    Long getRoleIdByName(String name);
}
