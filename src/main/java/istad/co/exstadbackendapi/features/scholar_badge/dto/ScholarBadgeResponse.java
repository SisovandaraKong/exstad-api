package istad.co.exstadbackendapi.features.scholar_badge.dto;

import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;

import java.time.LocalDate;

public record ScholarBadgeResponse(
        String uuid,
        LocalDate completionDate,
        BadgeResponse badge
) {
}


