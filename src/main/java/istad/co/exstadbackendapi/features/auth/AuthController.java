package istad.co.exstadbackendapi.features.auth;

import istad.co.exstadbackendapi.features.auth.dto.LoginRequest;
import istad.co.exstadbackendapi.features.auth.dto.RegisterRequest;
import istad.co.exstadbackendapi.features.auth.dto.KeycloakUserResponse;
import istad.co.exstadbackendapi.features.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public KeycloakUserResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
