package bootcamp.authenticationservice.application.jpa.mapper;

import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper (componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IUserEntityMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "authorities",ignore = true)
    @Mapping(target = "role",ignore = true)
    UserEntity toEntity(User user);

    @Mapping(target = "role",ignore = true)
    User toDomain(UserEntity userEntity);
}
