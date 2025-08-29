package istad.co.exstadbackendapi.features.user.dto;

import istad.co.exstadbackendapi.enums.Role;

import java.time.LocalDate;

public record UserRequest(
        String username,
        String email,
        String password,
        String cfPassword,
        String englishName,
        String khmerName,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dob,
        Role role
) {
}
