package istad.co.exstadbackendapi.features.university.dto;

public record UniversityResponse(
        String uuid,
        String englishName,
        String khmerName,
        String shortName
) {
}
