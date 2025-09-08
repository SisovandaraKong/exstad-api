package istad.co.exstadbackendapi.features.certificate.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record CertificateResponse(
        String scholarUuid,
        String openingProgramUuid,
        String tempCertificateUrl,
        String certificateUrl,
        boolean isVerified,
        AuditableDto audit
) {
}
