package co.istad.exstadapi.features.document.dto;

import co.istad.exstadapi.audit.AuditableDto;
import co.istad.exstadapi.enums.DocumentType;
import co.istad.exstadapi.enums.OfferingType;
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
