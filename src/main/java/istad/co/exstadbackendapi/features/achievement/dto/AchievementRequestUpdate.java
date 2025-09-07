package istad.co.exstadbackendapi.features.achievement.dto;

import istad.co.exstadbackendapi.enums.AchievementType;

public record AchievementRequestUpdate(
        String title,
        String description,
        String icon,
        String video,
        String link,
        String tag,
        AchievementType achievementType
) {
}
