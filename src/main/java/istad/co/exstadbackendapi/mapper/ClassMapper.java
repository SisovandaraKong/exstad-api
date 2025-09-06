package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Class;
import istad.co.exstadbackendapi.features.classes.dto.ClassRequest;
import istad.co.exstadbackendapi.features.classes.dto.ClassResponse;
import istad.co.exstadbackendapi.features.classes.dto.ClassUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        AuditableMapper.class, MapperHelper.class
})
public interface ClassMapper {

    @Mapping(target = "openingProgramName", source = "openingProgram.title")
    @Mapping(target = "audit", source = "aClass")
    ClassResponse toClassResponse(Class aClass);

    @Mapping(target = "openingProgram", source = "openingProgramUuid", qualifiedByName = "toOpeningProgramTitle")
    Class fromClassRequest(ClassRequest classRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateClassFromRequest(ClassUpdate classUpdate,@MappingTarget Class aClass);
}
