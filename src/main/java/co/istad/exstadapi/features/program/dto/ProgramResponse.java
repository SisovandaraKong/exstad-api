package co.istad.exstadapi.features.program.dto;

import co.istad.exstadapi.audit.AuditableDto;
import co.istad.exstadapi.domain.vo.*;
import co.istad.exstadapi.enums.ProgramLevel;
import co.istad.exstadapi.enums.ProgramType;

import java.util.List;

public record ProgramResponse(
        String uuid,
        String title,
        String slug,
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
        ProgramLevel programLevel,
        AuditableDto audit
) {
}
