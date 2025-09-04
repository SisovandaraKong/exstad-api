package istad.co.exstadbackendapi.features.currenAddress.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record CurrentAddressResponse(
        String uuid,
        String englishName,
        String khmerName,
        String province,
        Long scholars,
        Boolean isDeleted,
        AuditableDto audit
) {
}
