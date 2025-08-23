package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.domain.User;
import istad.co.exstadbackendapi.features.auth.AuthService;
import istad.co.exstadbackendapi.features.auth.dto.RegisterRequest;
import istad.co.exstadbackendapi.features.auth.dto.KeycloakUserResponse;
import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import istad.co.exstadbackendapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if(!userRequest.password().equals(userRequest.cfPassword())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Passwords do not match");
        }
        RegisterRequest register = new RegisterRequest(
                userRequest.username(),
                userRequest.email(),
                userRequest.password(),
                userRequest.cfPassword(),
                userRequest.englishName(),
                userRequest.khmerName()
        );

        KeycloakUserResponse response = authService.register(register);

        User user = userMapper.toUser(userRequest);
        user.setUuid(response.uuid());

        user = userRepository.save(user);

        return userMapper.fromUser(user);
    }
}
