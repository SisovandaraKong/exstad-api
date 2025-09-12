package co.istad.exstadapi.features.openingProgram.dto;

import co.istad.exstadapi.domain.vo.*;
import co.istad.exstadapi.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record OpeningProgramRequest(
        @NotBlank(message = "Program UUID is required")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "Program UUID must be a valid UUID format")
        String programUuid,

        @Size(max = 500, message = "Thumbnail URL must not exceed 500 characters")
        String thumbnail,

        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 60, message = "Title must be between 3 and 60 characters")
        String title,

        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Slug must be lowercase alphanumeric with hyphens")
        @Size(min = 3, max = 100, message = "Slug must be between 3 and 100 characters")
        String slug,

        @NotNull(message = "Total slot is required")
        @Min(value = 1, message = "Total slot must be at least 1")
        @Max(value = 1000, message = "Total slot must not exceed 1000")
        Integer totalSlot,

        @NotNull(message = "Original fee is required")
        @DecimalMin(value = "0.0", message = "Original fee must be zero or positive")
        @Digits(integer = 8, fraction = 2, message = "Original fee must have at most 8 integer digits and 2 decimal places")
        BigDecimal originalFee,

        @NotNull(message = "Scholarship is required")
        @DecimalMin(value = "0.0", message = "Scholarship must be zero or positive")
        @Digits(integer = 3, fraction = 2, message = "Scholarship must have at most 3 integer digits and 2 decimal places")
        BigDecimal scholarship,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", message = "Price must be zero or positive")
        @Digits(integer = 8, fraction = 2, message = "Price must have at most 8 integer digits and 2 decimal places")
        BigDecimal price,

        @Size(max = 200, message = "Telegram group must not exceed 200 characters")
        String telegramGroup,

        @NotNull(message = "Generation is required")
        @Min(value = 1, message = "Generation must be at least 1")
        Integer generation,

        @NotNull(message = "Status is required")
        Status status,

        @Size(max = 500, message = "QR code URL must not exceed 500 characters")
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

        @NotBlank(message = "Duration is required")
        @Size(min = 1, max = 100, message = "Duration must be between 1 and 100 characters")
        String duration,

        @Valid
        List<Activity> activities,

        @Size(max = 500, message = "Curriculum PDF URI must not exceed 500 characters")
        String curriculumPdfUri
) {
}
