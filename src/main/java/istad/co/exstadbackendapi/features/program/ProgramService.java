package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.base.BasedMessage;
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
}
