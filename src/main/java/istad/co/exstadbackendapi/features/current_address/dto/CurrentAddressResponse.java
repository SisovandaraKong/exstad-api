package istad.co.exstadbackendapi.features.current_address.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record CurrentAddressResponse(
        String uuid,
        String englishName,
        String khmerName,
        String province,
        Long scholars,
        AuditableDto audit
) {
}
