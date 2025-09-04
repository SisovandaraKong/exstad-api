package istad.co.exstadbackendapi.features.openingProgram;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.OpeningProgram;
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
