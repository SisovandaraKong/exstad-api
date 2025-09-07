package istad.co.exstadbackendapi.features.enrollment.dto;

import istad.co.exstadbackendapi.enums.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Map;

public record EnrollmentRequest(

        @NotBlank(message = "English name is required")
        @Size(max = 100, message = "English name must be at most 100 characters")
        String englishName,

        @NotBlank(message = "Khmer name is required")
        @Size(max = 100, message = "Khmer name must be at most 100 characters")
        String khmerName,

        String openingProgramUuid,

        @NotNull(message = "Gender is required")
        Gender gender,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dob,

        @NotBlank(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must be at most 20 characters")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        String avatar,

        @NotBlank(message = "Province is required")
        @Size(max = 50, message = "Province must be at most 50 characters")
        String province,

        @NotBlank(message = "Current address is required")
        @Size(max = 200, message = "Current address must be at most 200 characters")
        String currentAddress,

        @NotBlank(message = "University is required")
        @Size(max = 100, message = "University must be at most 100 characters")
        String university,

        @NotBlank(message = "Education qualification is required")
        @Size(max = 100, message = "Education qualification must be at most 100 characters")
        String educationQualification,

        Map<String, String> extra
) {}
