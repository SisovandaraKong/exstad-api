package istad.co.exstadbackendapi.features.user.dto;

import istad.co.exstadbackendapi.enums.Role;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String username,
        String email,
        String englishName,
        String khmerName,
        String gender,
        LocalDate dob,
        Role role

) {
}
