package co.istad.exstadapi.features.openingProgram;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.domain.vo.*;
import co.istad.exstadapi.features.openingProgram.dto.OpeningProgramRequest;
import co.istad.exstadapi.features.openingProgram.dto.OpeningProgramResponse;
import co.istad.exstadapi.features.openingProgram.dto.OpeningProgramUpdate;
import co.istad.exstadapi.features.openingProgram.dto.SetUpTemplate;

import java.util.List;

public interface OpeningProgramService {

    List<OpeningProgramResponse> getAllOpeningPrograms();
    OpeningProgramResponse getOpeningProgramByUuid(String uuid);
    OpeningProgramResponse getOpeningProgramByTitle(String title);
    OpeningProgramResponse createOpeningProgram(OpeningProgramRequest openingProgramRequest);
    OpeningProgramResponse updateOpeningProgram(String uuid, OpeningProgramUpdate openingProgramUpdate);
    BasedMessage deleteSoftOpeningProgram(String uuid);
    BasedMessage restoreOpeningProgram(String uuid);
    BasedMessage deleteHardOpeningProgram(String uuid);
    BasedMessage activateOpeningProgram(String uuid);
    BasedMessage deactivateOpeningProgram(String uuid);
    String setUpTemplate(String uuid, SetUpTemplate setUpTemplate);
    OpeningProgramResponse setUpActivities(String uuid, List<Activity> activities);
    OpeningProgramResponse setUpTimelines(String uuid, List<Timeline> timelines);
    OpeningProgramResponse setUpCurricula(String uuid, List<Curriculum> curricula);
    OpeningProgramResponse setUpRoadmaps(String uuid, List<Roadmap> roadmaps);
    OpeningProgramResponse setUpLearningOutcomes(String uuid, List<LearningOutcome> learningOutcomes);
    OpeningProgramResponse setUpRequirements(String uuid, List<Requirement> requirements);
    OpeningProgramResponse setUpDetails(String uuid, List<Detail> details);
    List<Activity> getActivities(String uuid);
    List<Timeline> getTimelines(String uuid);
    List<Curriculum> getCurricula(String uuid);
    List<Roadmap> getRoadmaps(String uuid);
    List<LearningOutcome> getLearningOutcomes(String uuid);
    List<Requirement> getRequirements(String uuid);
    List<Detail> getDetails(String uuid);
}
