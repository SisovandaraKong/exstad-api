package istad.co.exstadbackendapi.audit;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuditableDto(
        String createdBy,
        String updatedBy,
        Instant createdAt,
        Instant updatedAt
) {
}