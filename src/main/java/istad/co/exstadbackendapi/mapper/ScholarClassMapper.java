package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.ScholarClass;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassRequest;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassResponse;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        AuditableMapper.class, MapperHelper.class,ScholarMapper.class, ClassMapper.class
})
public interface ScholarClassMapper {

    @Mapping(target = "audit", source = "scholarClass")
    @Mapping(target = "scholarUuid", source = "scholar.uuid")
    @Mapping(target = "classUuid", source = "AClass.uuid")
    @Mapping(target = "scholarName", source = "scholar.user.englishName")
    @Mapping(target = "className", source = "AClass.className")
    ScholarClassResponse toScholarClassResponse(ScholarClass scholarClass);

    @Mapping(target = "AClass", source = "classUuid", qualifiedByName = "toClassByUuid")
    @Mapping(target = "scholar", source = "scholarUuid", qualifiedByName = "toScholar")
    ScholarClass toScholarClassRequest(ScholarClassRequest scholarClassRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateScholarClassFromRequest(ScholarClassUpdate scholarClassUpdate, @MappingTarget ScholarClass scholarClass);
}
