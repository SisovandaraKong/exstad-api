package istad.co.exstadbackendapi.features.scholarAchievement.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;

public record ScholarAchievementResponseForAchievement(
        String uuid,
        ScholarResponse scholar,
        AuditableDto audit
) {
}
