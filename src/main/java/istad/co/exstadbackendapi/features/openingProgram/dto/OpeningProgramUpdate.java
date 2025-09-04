package istad.co.exstadbackendapi.features.openingProgram.dto;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;
public record OpeningProgramUpdate(
        @Size(min = 3, max = 60, message = "Title must be between 3 and 60 characters")
        String title,

        String thumbnail,

        @Min(value = 1, message = "Total slot must be at least 1")
        Integer totalSlot,

        @PositiveOrZero(message = "Original fee must be zero or positive")
        Float originalFee,

        @PositiveOrZero(message = "Scholarship must be zero or positive")
        Float scholarship,

        @PositiveOrZero(message = "Price must be zero or positive")
        Float price,

        String telegramGroup,

        @Min(value = 1, message = "Generation must be at least 1")
        Integer generation,

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
