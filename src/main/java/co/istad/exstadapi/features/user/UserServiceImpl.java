package co.istad.exstadapi.features.user;

import co.istad.exstadapi.domain.User;
import co.istad.exstadapi.features.auth.AuthService;
import co.istad.exstadapi.features.auth.dto.RegisterRequest;
import co.istad.exstadapi.features.auth.dto.KeycloakUserResponse;
import co.istad.exstadapi.features.user.dto.UserRequest;
import co.istad.exstadapi.features.user.dto.UserResponse;
import co.istad.exstadapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if(userRepository.existsByUsername(userRequest.username())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if(userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if(!userRequest.password().equals(userRequest.cfPassword())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Passwords do not match");
        }
        RegisterRequest register = new RegisterRequest(
                userRequest.username(),
                userRequest.email(),
                userRequest.password(),
                userRequest.cfPassword(),
                userRequest.englishName(),
                userRequest.khmerName(),
                userRequest.role()
        );

        KeycloakUserResponse response = authService.register(register);

        User user = userMapper.toUser(userRequest);
        user.setUuid(response.uuid());
        user.setIsDeleted(false);

        user = userRepository.save(user);

        return userMapper.fromUser(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userMapper.fromUser(userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        ));
    }


    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(userMapper::fromUser)
                .toList();
    }

    @Override
    public UserResponse getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));
        return userMapper.fromUser(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));
        return userMapper.fromUser(user);
    }

    @Override
    public UserResponse getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof org.springframework.security.oauth2.jwt.Jwt jwt) {
            username = jwt.getClaimAsString("preferred_username");
            if (username == null) {
                username = jwt.getClaimAsString("email");
            }
        } else if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }


        return getUserByUsername(username);
    }

    @Override
    public String getUsernameByUuid(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")).getUsername();

    }
}
