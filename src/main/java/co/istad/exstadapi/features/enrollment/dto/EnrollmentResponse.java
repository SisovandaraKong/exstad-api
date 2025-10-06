package co.istad.exstadapi.features.enrollment.dto;

import co.istad.exstadapi.enums.Gender;
import co.istad.exstadapi.features.classes.dto.ClassResponse;

import java.time.LocalDate;
import java.util.Map;

public record EnrollmentResponse(
        String uuid,
        String englishName,
        String khmerName,
        String program,
        ClassResponse _class,
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
        Boolean isInterviewed,
        Boolean isAchieved,
        Boolean isPassed

) {
}
