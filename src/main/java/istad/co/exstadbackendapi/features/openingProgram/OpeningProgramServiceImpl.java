package istad.co.exstadbackendapi.features.openingProgram;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.OpeningProgram;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramRequest;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramResponse;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramUpdate;
import istad.co.exstadbackendapi.mapper.OpeningProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OpeningProgramServiceImpl implements OpeningProgramService {
    private final OpeningProgramRepository openingProgramRepository;
    private final OpeningProgramMapper openingProgramMapper;

    @Override
    public List<OpeningProgramResponse> getAllOpeningPrograms() {
        List<OpeningProgram> openingPrograms = openingProgramRepository.findAll();
        return openingPrograms.
                stream()
                .map(openingProgramMapper::toOpeningProgramResponse)
                .toList();
    }

    @Override
    public OpeningProgramResponse getOpeningProgramByUuid(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse getOpeningProgramByTitle(String title) {
        OpeningProgram openingProgram = openingProgramRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse createOpeningProgram(OpeningProgramRequest openingProgramRequest) {
        OpeningProgram openingProgram = openingProgramMapper.fromOpeningProgramRequest(openingProgramRequest);
        openingProgram.setAchievements(null);
        openingProgram.setCurricula(null);
        openingProgram.setDetails(null);
        openingProgram.setActivities(null);
        openingProgram.setTimelines(null);
        openingProgram.setLearningOutcomes(null);
        openingProgram.setRequirements(null);

        openingProgram.setIsActive(true);
        openingProgram.setUuid(UUID.randomUUID().toString());
        openingProgram.setIsDeleted(false);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse updateOpeningProgram(String uuid, OpeningProgramUpdate openingProgramUpdate) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgramMapper.updateOpeningProgramFromRequest(openingProgramUpdate, openingProgram);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Transactional
    @Override
    public BasedMessage deleteSoftOpeningProgram(String uuid) {
        if (!openingProgramRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found");
        }
        openingProgramRepository.softDeleteByUuid(uuid);
        return new BasedMessage("Opening Program soft deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage deleteHardOpeningProgram(String uuid) {
        if (!openingProgramRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found");
        }
        openingProgramRepository.deleteByUuid(uuid);
        return new BasedMessage("Opening Program hard deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage activateOpeningProgram(String uuid) {
        if (!openingProgramRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found");
        }
        openingProgramRepository.activateByUuid(uuid);
        return new BasedMessage("Opening Program activated successfully");
    }

    @Transactional
    @Override
    public BasedMessage deactivateOpeningProgram(String uuid) {
        if (!openingProgramRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found");
        }
        openingProgramRepository.deactivateByUuid(uuid);
        return new BasedMessage("Opening Program deactivated successfully");
    }

    @Override
    public OpeningProgramResponse setUpActivities(String uuid, List<Activity> activities) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setActivities(activities);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpTimelines(String uuid, List<Timeline> timelines) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setTimelines(timelines);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpCurricula(String uuid, List<Curriculum> curricula) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setCurricula(curricula);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpRoadmaps(String uuid, List<Roadmap> roadmaps) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setRoadmaps(roadmaps);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpLearningOutcomes(String uuid, List<LearningOutcome> learningOutcomes) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setLearningOutcomes(learningOutcomes);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpRequirements(String uuid, List<Requirement> requirements) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setRequirements(requirements);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public OpeningProgramResponse setUpDetails(String uuid, List<Detail> details) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        openingProgram.setDetails(details);
        openingProgram = openingProgramRepository.save(openingProgram);
        return openingProgramMapper.toOpeningProgramResponse(openingProgram);
    }

    @Override
    public List<Activity> getActivities(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getActivities();
    }

    @Override
    public List<Timeline> getTimelines(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getTimelines();
    }

    @Override
    public List<Curriculum> getCurricula(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getCurricula();
    }

    @Override
    public List<Roadmap> getRoadmaps(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getRoadmaps();
    }

    @Override
    public List<LearningOutcome> getLearningOutcomes(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getLearningOutcomes();
    }

    @Override
    public List<Requirement> getRequirements(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getRequirements();
    }

    @Override
    public List<Detail> getDetails(String uuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found"));
        return openingProgram.getDetails();
    }

    @Transactional
    @Override
    public BasedMessage restoreOpeningProgram(String uuid) {
        if (!openingProgramRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found");
        }
        openingProgramRepository.restoreByUuid(uuid);
        return new BasedMessage("Opening Program restored successfully");
    }
}
