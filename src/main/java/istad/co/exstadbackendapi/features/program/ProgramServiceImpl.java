package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.Program;
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
}
