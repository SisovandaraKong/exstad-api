package istad.co.exstadbackendapi.features.achievement.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.enums.AchievementType;

public record AchievementResponse(
        String uuid,
        String title,
        String description,
        String icon,
        String program,
        AchievementType achievementType,
        String tag,
        String video,
        String link,
        AuditableDto audit
) {
}
