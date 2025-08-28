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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        return null;
    }

    @Override
    public ProgramResponse createProgram(ProgramRequest programRequest) {
        return null;
    }

    @Override
    public ProgramResponse updateProgram(String uuid, ProgramUpdate programUpdate) {
        return null;
    }

    @Override
    public BasedMessage deleteProgram(String uuid) {
        return null;
    }
}
