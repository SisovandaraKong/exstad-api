package istad.co.exstadbackendapi.features.program.dto;

import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.ProgramLevel;
import istad.co.exstadbackendapi.enums.ProgramType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProgramUpdate(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
        String description,

        @Size(max = 255, message = "Thumbnail URL must be max 255 characters")
        String thumbnailUrl,

        @Size(max = 100, min = 4, message = "Background color must be min 4 and max 100 characters")
        String bgColor,

        List<Highlight> highlights,

        List<ProgramOverview> programOverviews,

        @Size(max = 255, message = "Curriculum PDF URL must be max 255 characters")
        String curriculumPdfUrl,

        List<Roadmap> roadmaps,

        List<Faq> faqs,

        List<Requirement> requirements,

        List<LearningOutcome> learningOutcomes,

        List<Timeline> timelines,

        @NotNull(message = "Program type is required")
        ProgramType programType,

        List<Curriculum> curricula,

        @NotNull(message = "Program level is required")
        ProgramLevel programLevel
) {
}
