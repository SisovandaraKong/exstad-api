package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.Program;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.features.program.dto.ProgramRequest;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import istad.co.exstadbackendapi.features.program.dto.ProgramUpdate;
import istad.co.exstadbackendapi.mapper.ProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    @Override
    public List<ProgramResponse> getAllPrograms() {
        List<Program> programs = programRepository.findAll();
        return programs
                .stream()
                .map(programMapper::toProgramResponse)
                .toList();
    }

    @Override
    public ProgramResponse getProgramByUuid(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Program not found"
                ));
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse getProgramByTitle(String title) {
        Program program = programRepository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Program not found"
                ));
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        Program program = programMapper.fromProgramRequest(programRequest);
        program.setHighlights(null);
        program.setProgramOverviews(null);
        program.setRoadmaps(null);
        program.setFaqs(null);
        program.setRequirements(null);
        program.setLearningOutcomes(null);
        program.setCurricula(null);
        program.setUuid(UUID.randomUUID().toString());
        program.setIsDeleted(false);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse updateProgram(String uuid, ProgramUpdate programUpdate) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Program not found"
                ));
        programMapper.toProgramUpdate(programUpdate, program);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Transactional
    @Override
    public BasedMessage deleteProgram(String uuid) {
        if (!programRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Program not found"
            );
        }
        programRepository.softDeleteByUuid(uuid);
        return new BasedMessage("Program deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage restoreProgram(String uuid) {
        if (!programRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Program not found"
            );
        }
        programRepository.undeleteByUuid(uuid);
        return new BasedMessage("Program restored successfully");
    }

    @Transactional
    @Override
    public BasedMessage hardDeleteProgram(String uuid) {
        if (!programRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Program not found"
            );
        }
        programRepository.deleteByUuid(uuid);
        return new BasedMessage("Program hard deleted successfully");
    }

    @Override
    public ProgramResponse setUpHighlights(String uuid, List<Highlight> highlights) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Program not found"
                ));
        program.setHighlights(highlights);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpProgramOverviews(String uuid, List<ProgramOverview> overviews) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setProgramOverviews(overviews);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpRoadmaps(String uuid, List<Roadmap> roadmaps) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setRoadmaps(roadmaps);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpFaqs(String uuid, List<Faq> faqs) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setFaqs(faqs);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpRequirements(String uuid, List<Requirement> requirements) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setRequirements(requirements);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpLearningOutcomes(String uuid, List<LearningOutcome> learningOutcomes) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setLearningOutcomes(learningOutcomes);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public ProgramResponse setUpCurricula(String uuid, List<Curriculum> curricula) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        program.setCurricula(curricula);
        program = programRepository.save(program);
        return programMapper.toProgramResponse(program);
    }

    @Override
    public List<Highlight> getHighlights(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getHighlights();
    }

    @Override
    public List<ProgramOverview> getProgramOverviews(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getProgramOverviews();
    }

    @Override
    public List<Roadmap> getRoadmaps(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getRoadmaps();
    }

    @Override
    public List<Faq> getFaqs(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getFaqs();
    }

    @Override
    public List<Requirement> getRequirements(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getRequirements();
    }

    @Override
    public List<LearningOutcome> getLearningOutcomes(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getLearningOutcomes();
    }

    @Override
    public List<Curriculum> getCurricula(String uuid) {
        Program program = programRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found"));
        return program.getCurricula();
    }


}
