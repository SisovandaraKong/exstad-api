package istad.co.exstadbackendapi.features.badge.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;

public record BadgeResponse(
        String uuid,
        String badgeImage,
        String title,
        String description,
        Boolean isDeleted,
        AuditableDto audit
) {
}
