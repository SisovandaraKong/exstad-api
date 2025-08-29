package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUserByUsername(String username);
    UserResponse getCurrentUser();
    String getUsernameByUuid(String uuid);
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserByUuid(String uuid);
    UserResponse getUserByEmail(String email);

}
