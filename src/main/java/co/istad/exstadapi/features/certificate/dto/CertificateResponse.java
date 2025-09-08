package co.istad.exstadapi.features.certificate.dto;

import co.istad.exstadapi.audit.AuditableDto;

public record CertificateResponse(
        String certificateUrl,
        boolean isVerified,
        AuditableDto audit
) {
}
