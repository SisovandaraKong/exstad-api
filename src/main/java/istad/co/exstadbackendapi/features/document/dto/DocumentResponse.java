package istad.co.exstadbackendapi.features.document.dto;

import istad.co.exstadbackendapi.enums.DocumentType;
import lombok.Builder;

@Builder
public record DocumentResponse(
        String name,
        String mimeType,
        DocumentType documentType,
        Long fileSize,
        String uri
) {
}
