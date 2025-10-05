package co.istad.exstadapi.features.enrollment.dto;

import co.istad.exstadapi.enums.Gender;

import java.time.LocalDate;
import java.util.Map;

public record EnrollmentResponse(
        String uuid,
//        String classRoom,
//        String classShift,
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
        Boolean isInterviewed,
        Boolean isAchieved,
        Boolean isPassed

) {
}
