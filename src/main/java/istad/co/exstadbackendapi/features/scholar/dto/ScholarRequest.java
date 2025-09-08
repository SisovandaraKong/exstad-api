package istad.co.exstadbackendapi.features.scholar.dto;

import istad.co.exstadbackendapi.enums.Gender;
import istad.co.exstadbackendapi.enums.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ScholarRequest(
        @NotBlank(message = "Username is required")
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
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
        String englishName,

        @NotBlank(message = "Khmer name is required")
        @Size(min = 1, max = 100, message = "Khmer name must be between 1 and 100 characters")
        String khmerName,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dob,


        @NotBlank(message = "University is required")
        @Size(min = 2, max = 100, message = "University must be between 2 and 100 characters")
        String university,

        @NotBlank(message = "University is required")
        @Size(min = 2, max = 100, message = "University must be between 2 and 100 characters")
        String province,

        @NotBlank(message = "Current address is required")
        @Size(min = 3, max = 200, message = "Current address must be between 3 and 200 characters")
        String currentAddress,

        @Size(max = 50, message = "Nickname must not exceed 50 characters")
        String nickname,

        @Size(max = 500, message = "Bio must not exceed 500 characters")
        String bio,

        @Size(max = 255, message = "Avatar URL must be at most 255 characters")
        String avatar,

        @Size(min = 6, max = 20, message = "Phone family number must be between 6 and 20 characters")
        String phoneFamilyNumber,

        @NotNull(message = "Is Public is required")
        Boolean isPublic,

        @Size(max = 300, message = "Quote must not exceed 300 characters")
        String quote
) {
}
