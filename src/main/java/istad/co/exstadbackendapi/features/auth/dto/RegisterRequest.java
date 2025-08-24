package istad.co.exstadbackendapi.features.auth.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String cfPassword,
        String englishName,
        String khmerName
) {
}
