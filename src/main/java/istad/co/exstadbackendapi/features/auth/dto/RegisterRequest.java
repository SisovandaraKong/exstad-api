package istad.co.exstadbackendapi.features.auth.dto;

import istad.co.exstadbackendapi.enums.Role;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String cfPassword,
        String englishName,
        String khmerName,
        Role role
) {
}
