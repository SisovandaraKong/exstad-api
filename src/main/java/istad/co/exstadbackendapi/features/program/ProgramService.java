package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.features.program.dto.ProgramRequest;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import istad.co.exstadbackendapi.features.program.dto.ProgramUpdate;

import java.util.List;

public interface ProgramService {
    List<ProgramResponse> getAllPrograms();
    ProgramResponse getProgramByUuid(String uuid);
    ProgramResponse getProgramByTitle(String title);
    ProgramResponse createProgram(ProgramRequest programRequest);
    ProgramResponse updateProgram(String uuid, ProgramUpdate programUpdate);
    BasedMessage deleteProgram(String uuid);
    BasedMessage restoreProgram(String uuid);
    BasedMessage hardDeleteProgram(String uuid);
    ProgramResponse setUpHighlights(String uuid, List<Highlight> highlights);
    ProgramResponse setUpProgramOverviews(String uuid, List<ProgramOverview> overviews);
    ProgramResponse setUpRoadmaps(String uuid, List<Roadmap> roadmaps);
    ProgramResponse setUpFaqs(String uuid, List<Faq> faqs);
    ProgramResponse setUpRequirements(String uuid, List<Requirement> requirements);
    ProgramResponse setUpLearningOutcomes(String uuid, List<LearningOutcome> learningOutcomes);
    ProgramResponse setUpCurricula(String uuid, List<Curriculum> curricula);
    List<Highlight> getHighlights(String uuid);
    List<ProgramOverview> getProgramOverviews(String uuid);
    List<Roadmap> getRoadmaps(String uuid);
    List<Faq> getFaqs(String uuid);
    List<Requirement> getRequirements(String uuid);
    List<LearningOutcome> getLearningOutcomes(String uuid);
    List<Curriculum> getCurricula(String uuid);
}
