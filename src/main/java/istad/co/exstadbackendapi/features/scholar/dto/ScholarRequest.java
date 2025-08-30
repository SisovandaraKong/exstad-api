package istad.co.exstadbackendapi.features.scholar.dto;

import istad.co.exstadbackendapi.enums.Gender;
import istad.co.exstadbackendapi.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ScholarRequest(
        @NotBlank(message = "Username is required")
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, dots, underscores, and hyphens")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be at most 100 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
        String password,

        @NotBlank(message = "Confirm password is required")
        String cfPassword,

        @NotBlank(message = "English name is required")
        @Size(min = 1, max = 100, message = "English name must be between 1 and 100 characters")
        @Pattern(regexp = "^[a-zA-Z\\s.'-]+$", message = "English name can only contain letters, spaces, dots, apostrophes, and hyphens")
        String englishName,

        @NotBlank(message = "Khmer name is required")
        @Size(min = 1, max = 100, message = "Khmer name must be between 1 and 100 characters")
        String khmerName,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dob,


        @NotNull(message = "University is required")
        String university,

        @NotNull(message = "Province is required")
        String province,

        @NotNull(message = "Current Address is required")
        String currentAddress,

        String nickname,

        String bio,

        String avatar,

        String phoneFamilyNumber,

        @NotNull(message = "Is Public is required")
        Boolean isPublic,

        String quote
) {
}
