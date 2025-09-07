package istad.co.exstadbackendapi.features.scholarBadge.dto;

import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;

import java.time.LocalDate;

public record ScholarBadgeForScholarResponse(
        String uuid,
        LocalDate completionDate,
        BadgeResponse badge
) {
}
