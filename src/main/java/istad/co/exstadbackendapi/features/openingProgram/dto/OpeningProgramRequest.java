package istad.co.exstadbackendapi.features.openingProgram.dto;

import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record OpeningProgramRequest(
        @NotBlank(message = "Program uuid is required")
        String programUuid,

        String thumbnail,

        @NotNull(message = "Total slot is required")
        @Min(value = 1, message = "Total slot must be at least 1")
        Integer totalSlot,

        @NotNull(message = "Original fee is required")
        @PositiveOrZero(message = "Original fee must be zero or positive")
        Float originalFee,

        @NotNull(message = "Scholarship is required")
        @PositiveOrZero(message = "Scholarship must be zero or positive")
        Float scholarship,

        @NotNull(message = "Price is required")
        @PositiveOrZero(message = "Price must be zero or positive")
        Float price,

        String telegramGroup,

        @NotNull(message = "Generation is required")
        @Min(value = 1, message = "Generation must be at least 1")
        Integer generation,

        @NotNull(message = "isDeleted is required")
        Boolean isDeleted,

        Status status,

        String qrCodeUrl,

        @Valid
        List<Detail> details,

        @Valid
        List<Timeline> timelines,

        @Valid
        List<Curriculum> curricula,

        @Valid
        List<Roadmap> roadmaps,

        @Valid
        List<LearningOutcome> learningOutcomes,

        @Valid
        List<Requirement> requirements,

        String duration,

        @Valid
        List<Activity> activities,

        String curriculumPdfUri
) {
}
