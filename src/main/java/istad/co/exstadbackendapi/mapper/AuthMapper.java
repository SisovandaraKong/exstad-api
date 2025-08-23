package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.features.auth.dto.KeycloakUserResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(source = "firstName", target = "englishName")
    @Mapping(source = "lastName", target = "khmerName")
    @Mapping(source = "id", target = "uuid")
    KeycloakUserResponse toKeycloakUserResponse(UserRepresentation userRepresentation);
}
