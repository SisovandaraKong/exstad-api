package istad.co.exstadbackendapi.features.university.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record UniversityResponse(
        String uuid,
        String englishName,
        String khmerName,
        String shortName,
        Boolean isDeleted,
        AuditableDto audit
) {
}
