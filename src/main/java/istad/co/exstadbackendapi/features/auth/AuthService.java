package istad.co.exstadbackendapi.features.auth;

import istad.co.exstadbackendapi.features.auth.dto.LoginRequest;
import istad.co.exstadbackendapi.features.auth.dto.RegisterRequest;
import istad.co.exstadbackendapi.features.auth.dto.KeycloakUserResponse;
import istad.co.exstadbackendapi.features.auth.dto.TokenResponse;

import java.util.Optional;

public interface AuthService {

    KeycloakUserResponse register(RegisterRequest registerRequest);
    boolean delete(String uuid);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse refreshToken(String refreshToken);
    void logout(String refreshToken);
    Optional<KeycloakUserResponse> findByUuid(String uuid);
    Optional<KeycloakUserResponse> findByEmail(String email);
    Optional<KeycloakUserResponse> findByUsername(String username);


}
