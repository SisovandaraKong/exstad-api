package istad.co.exstadbackendapi.features.certificate.dto;

import java.util.List;

public record CertificateRequestDto(
        List<String> scholarUuids,
        Integer openingProgramId,
        String bgImage
) {}
