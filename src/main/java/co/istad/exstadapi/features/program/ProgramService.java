package co.istad.exstadapi.features.program;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.domain.vo.*;
import co.istad.exstadapi.features.program.curriculum.dto.CurriculumSetUp;
import co.istad.exstadapi.features.program.dto.ProgramRequest;
import co.istad.exstadapi.features.program.dto.ProgramResponse;
import co.istad.exstadapi.features.program.dto.ProgramUpdate;
import co.istad.exstadapi.features.program.faq.dto.FaqSetUp;
import co.istad.exstadapi.features.program.highlight.dto.HighlightSetUp;

import java.util.List;

public interface ProgramService {
    List<ProgramResponse> getAllPrograms();
    ProgramResponse getProgramByUuid(String uuid);
    ProgramResponse getProgramBySlug(String slug);
    ProgramResponse getProgramByTitle(String title);
    ProgramResponse createProgram(ProgramRequest programRequest);
    ProgramResponse updateProgram(String uuid, ProgramUpdate programUpdate);
    BasedMessage deleteProgram(String uuid);
    BasedMessage restoreProgram(String uuid);
    BasedMessage hardDeleteProgram(String uuid);
    ProgramResponse setUpHighlights(String uuid, List<HighlightSetUp> highlightSetUps);
    ProgramResponse setUpProgramOverviews(String uuid, List<ProgramOverview> overviews);
    ProgramResponse setUpRoadmaps(String uuid, List<Roadmap> roadmaps);
    ProgramResponse setUpFaqs(String uuid, List<FaqSetUp> faqSetUps);
    ProgramResponse setUpRequirements(String uuid, List<Requirement> requirements);
    ProgramResponse setUpLearningOutcomes(String uuid, List<LearningOutcome> learningOutcomes);
    ProgramResponse setUpCurricula(String uuid, List<CurriculumSetUp> curriculumSetUps);
    List<Highlight> getHighlights(String uuid);
    List<ProgramOverview> getProgramOverviews(String uuid);
    List<Roadmap> getRoadmaps(String uuid);
    List<Faq> getFaqs(String uuid);
    List<Requirement> getRequirements(String uuid);
    List<LearningOutcome> getLearningOutcomes(String uuid);
    List<Curriculum> getCurricula(String uuid);
}
