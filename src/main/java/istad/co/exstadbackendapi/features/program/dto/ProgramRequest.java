package istad.co.exstadbackendapi.features.program.dto;

import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.ProgramLevel;
import istad.co.exstadbackendapi.enums.ProgramType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProgramRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
        String title,

        String slug,

        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
        String description,
        String thumbnailUrl,
        String bgColor,
        List<Highlight> highlights,
        List<ProgramOverview> programOverviews,
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
