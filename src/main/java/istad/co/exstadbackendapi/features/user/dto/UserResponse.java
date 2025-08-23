package istad.co.exstadbackendapi.features.user.dto;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String username,
        String email,
        String englishName,
        String khmerName,
        String gender,
        LocalDate dob

) {
}
