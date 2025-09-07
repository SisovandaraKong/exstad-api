package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.OpeningProgram;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramRequest;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramResponse;
import istad.co.exstadbackendapi.features.openingProgram.dto.OpeningProgramUpdate;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {AuditableMapper.class, MapperHelper.class})
public interface OpeningProgramMapper {

    @Mapping(target = "audit", source = "openingProgram")
    @Mapping(target = "programName", source = "program.title")
    OpeningProgramResponse toOpeningProgramResponse(OpeningProgram openingProgram);

    @Mapping(target = "program", source = "programUuid",
            qualifiedByName = "toProgramTitle")
    OpeningProgram fromOpeningProgramRequest(OpeningProgramRequest openingProgramRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOpeningProgramFromRequest(OpeningProgramUpdate openingProgramUpdate,
                                         @MappingTarget OpeningProgram openingProgram);
}

