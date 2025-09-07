package istad.co.exstadbackendapi.features.certificate.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record CertificateResponse(
        String certificateUrl,
        boolean isVerified,
        AuditableDto audit
) {
}
