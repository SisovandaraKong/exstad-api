package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
