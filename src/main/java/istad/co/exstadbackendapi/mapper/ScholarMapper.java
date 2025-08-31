package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequestUpdate;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, AuditableMapper.class, ScholarBadgeMapper.class})
public interface ScholarMapper {

    @Mapping(source = "university.englishName", target = "university")
    @Mapping(source = "province.englishName", target = "province")
    @Mapping(source = "currentAddress.englishName", target = "currentAddress")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.englishName", target = "englishName")
    @Mapping(source = "user.khmerName", target = "khmerName")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.role", target = "role")
    @Mapping(source = "scholarsBadges", target = "badges")
    @Mapping(source = ".", target = "audit")
    ScholarResponse fromScholar(Scholar scholar);

    @Mapping(source = "university", target = "university", qualifiedByName = "toUniversity")
    @Mapping(source = "currentAddress", target = "currentAddress", qualifiedByName = "toCurrentAddress")
    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    Scholar toScholar(ScholarRequest scholarRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "university", target = "university", qualifiedByName = "toUniversity")
    @Mapping(source = "currentAddress", target = "currentAddress", qualifiedByName = "toCurrentAddress")
    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    void toScholarPartially(ScholarRequestUpdate scholarRequestUpdate, @MappingTarget Scholar scholar);

}
