package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import jakarta.validation.Valid;
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
    @PostMapping("/register")
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(
                Map.of("users",userService.getAllUsers()), HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUserByUuid(@PathVariable String uuid) {
        return new ResponseEntity<>(
                userService.getUserByUuid(uuid), HttpStatus.OK
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(
                userService.getUserByEmail(email), HttpStatus.OK
        );
    }

}
