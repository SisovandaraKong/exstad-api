package istad.co.exstadbackendapi.features.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}
