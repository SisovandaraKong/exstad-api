package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.University;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequestUpdate;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class})
public interface UniversityMapper {

    University toUniversity(UniversityRequest universityRequest);

    @Mapping(target = "audit", source = "university")
    UniversityResponse fromUniversity(University university);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUniversityPartially(@MappingTarget University university, UniversityRequestUpdate universityRequestUpdate);
}
