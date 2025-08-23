package istad.co.exstadbackendapi.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
