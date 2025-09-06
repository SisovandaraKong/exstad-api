package istad.co.exstadbackendapi.features.scholarBadge.dto;

import java.time.LocalDate;

public record ScholarBadgeRequestUpdate(
        LocalDate completionDate
) {
}
