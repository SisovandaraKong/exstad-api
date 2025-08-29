package istad.co.exstadbackendapi.features.province.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record ProvinceResponse(
        String uuid,
        String englishName,
        String khmerName,
        AuditableDto audit
) {
}
