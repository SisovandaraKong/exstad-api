package istad.co.exstadbackendapi.features.enrollment.dto;

import istad.co.exstadbackendapi.enums.Gender;

import java.time.LocalDate;
import java.util.Map;

public record EnrollmentResponse(
        String uuid,
        String englishName,
        String khmerName,
        String program,
        Gender gender,
        LocalDate dob,
        String phoneNumber,
        String email,
        String avtar,
        String province,
        String currentAddress,
        String university,
        String educationQualification,
        Map<String, String> extra,
        Boolean isPaid,
        Boolean isAccepted,
        Boolean isAchieved,
        Boolean isPassed

) {
}
