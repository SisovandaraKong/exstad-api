package istad.co.exstadbackendapi.features.program.dto;

import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.ProgramLevel;
import istad.co.exstadbackendapi.enums.ProgramType;

import java.util.List;

public record ProgramUpdate(
        String title,
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
        ProgramType programType,
        List<Curriculum> curricula,
        ProgramLevel programLevel
) {
}
