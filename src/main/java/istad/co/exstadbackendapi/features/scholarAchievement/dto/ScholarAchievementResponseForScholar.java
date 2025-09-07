package istad.co.exstadbackendapi.features.scholarAchievement.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementResponse;

public record ScholarAchievementResponseForScholar(
        String uuid,
        AchievementResponse achievement,
        AuditableDto audit
) {
}
