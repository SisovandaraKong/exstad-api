package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.Program;
import istad.co.exstadbackendapi.features.program.dto.ProgramRequest;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(target = "audit", source = "program")
    ProgramResponse toProgramResponse(Program program);

    Program fromProgramRequest(ProgramRequest programRequest);
}
