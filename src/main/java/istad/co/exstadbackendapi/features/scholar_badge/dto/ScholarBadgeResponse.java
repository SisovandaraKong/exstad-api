package istad.co.exstadbackendapi.features.scholar_badge.dto;

import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;

import java.time.LocalDate;

public record ScholarBadgeResponse(
        String uuid,
        LocalDate completionDate,
        ScholarResponse scholar,
        BadgeResponse badge
) {
}


