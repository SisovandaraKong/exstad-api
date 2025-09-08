package istad.co.exstadbackendapi.features.openingProgram.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.Status;

import java.math.BigDecimal;
import java.util.List;

public record OpeningProgramResponse(
        String programName,
        String uuid,
        String title,
        String slug,
        String thumbnail,
        Integer totalSlot,
        BigDecimal originalFee,
        Float scholarship,
        Float price,
        String telegramGroup,
        Integer generation,
        String qrCodeUrl,
        List<Detail> details,
        List<Timeline> timelines,
        List<Curriculum> curricula,
        List<Roadmap> roadmaps,
        List<LearningOutcome> learningOutcomes,
        List<Requirement> requirements,
        String duration,
        List<Activity> activities,
        String curriculumPdfUri,
        Status status,
        Boolean isActive,
        Boolean isDeleted,
        AuditableDto audit
) {
}
