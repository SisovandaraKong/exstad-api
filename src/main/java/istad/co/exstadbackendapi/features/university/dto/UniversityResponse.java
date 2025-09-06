package istad.co.exstadbackendapi.features.university.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record UniversityResponse(
        String uuid,
        String englishName,
        String khmerName,
        String shortName,
        Long scholars,
        Boolean isDeleted,
        AuditableDto audit
) {
}
