package istad.co.exstadbackendapi.features.document.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.enums.OfferingType;
import lombok.Builder;

@Builder
public record DocumentResponse(
        String name,
        String mimeType,
        OfferingType offeringType,
        int gen,
        DocumentType documentType,
        Long fileSize,
        String uri,
        AuditableDto audit
) {
}
