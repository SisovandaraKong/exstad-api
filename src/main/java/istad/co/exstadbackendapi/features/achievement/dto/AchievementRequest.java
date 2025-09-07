package istad.co.exstadbackendapi.features.achievement.dto;

import istad.co.exstadbackendapi.enums.AchievementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AchievementRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be at most 100 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must be at most 500 characters")
        String description,

        @NotBlank(message = "Opening program UUID is required")
        String openingProgramUuid,

        @NotBlank(message = "Icon is required")
        String icon,

        @NotNull(message = "Achievement type is required")
        AchievementType achievementType,

        @NotBlank(message = "Tag is required")
        @Size(max = 50, message = "Tag must be at most 50 characters")
        String tag,

        @NotBlank(message = "Video URL is required")
        String video,

        @NotBlank(message = "Link is required")
        String link
) {}
