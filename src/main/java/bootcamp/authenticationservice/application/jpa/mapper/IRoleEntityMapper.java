package bootcamp.authenticationservice.application.jpa.mapper;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper (componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IRoleEntityMapper {
    Role toDomain(RoleEntity roleEntity);
    RoleEntity toEntity(Role role);
}
