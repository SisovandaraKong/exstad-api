package istad.co.exstadbackendapi.features.certificate.dto;

import java.util.List;

public record CertificateRequestDto(
        String scholarUuids,
        String openingProgramUuid,
        String bgImage
) {}
