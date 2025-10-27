package co.istad.exstadapi.features.applicantLetter.dto;

import java.sql.Date;

public record ApplicantLetterRequest(
        String khmerName,
        String englishName,
        String avatar,
        String gender,
        String national,
        Date dob,
        String placeOfBirth,
        String university,
        String year,
        String major,
        String educationQualification,
        String currentAddress,
        Date issueDate,
        String number,
        String tableNumber
) {
}

