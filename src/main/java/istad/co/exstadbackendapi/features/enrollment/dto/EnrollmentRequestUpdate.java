package istad.co.exstadbackendapi.features.enrollment.dto;

import istad.co.exstadbackendapi.enums.Gender;

import java.time.LocalDate;

public record EnrollmentRequestUpdate(
        String englishName,
        String khmerName,
        Gender gender,
        LocalDate dob,
        String email,
        String avtar,
        String province,
        String currentAddress,
        String university,
        String educationQualification,
        Boolean isPaid,
        Boolean isAccepted,
        Boolean isPassed,
        Boolean isAchieved
) {
}
