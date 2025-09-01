package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.University;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import istad.co.exstadbackendapi.features.university.dto.UniversityUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class})
public interface UniversityMapper {

    @Mapping(target = "audit", source = "university")
    @Mapping(target = "scholars", expression = "java(university.getScholars() != null ? (long) university.getScholars().size() : 0L)")
    UniversityResponse toUniversityResponse(University university);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateUniversityFromDto(UniversityUpdate universityUpdate,@MappingTarget University entity);
}
