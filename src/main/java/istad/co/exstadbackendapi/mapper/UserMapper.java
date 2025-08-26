package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.User;
import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse fromUser(User user);
    User toUser(UserRequest userRequest);
}
