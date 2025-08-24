package istad.co.exstadbackendapi.features.user.dto;

import java.time.LocalDate;

public record UserRequest(
        String username,
        String email,
        String password,
        String cfPassword,
        String englishName,
        String khmerName,
        String gender,
        LocalDate dob
) {
}
