package istad.co.exstadbackendapi.features.scholar_badge.dto;

import java.time.LocalDate;

public record ScholarBadgeRequestUpdate(
        LocalDate completionDate
) {
}
