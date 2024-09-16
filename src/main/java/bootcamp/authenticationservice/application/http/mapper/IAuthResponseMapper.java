package bootcamp.authenticationservice.application.http.mapper;

import bootcamp.authenticationservice.application.http.dto.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IAuthResponseMapper {
    AuthResponse toAuthResponse(String token);
}
