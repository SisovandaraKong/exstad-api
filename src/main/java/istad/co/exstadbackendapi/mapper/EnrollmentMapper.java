package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.Enrollment;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequest;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequestUpdate;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {MapperHelper.class})
public interface EnrollmentMapper {

    @Mapping(source = "university", target = "university", qualifiedByName = "toUniversity")
    @Mapping(source = "currentAddress", target = "currentAddress", qualifiedByName = "toCurrentAddress")
    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    @Mapping(source = "openingProgramUuid", target = "openingProgram", qualifiedByName = "toOpeningProgramByUuid")
    Enrollment toEnrollment(EnrollmentRequest enrollmentRequest);


    @Mapping(source = "university.englishName", target = "university")
    @Mapping(source = "province.englishName", target = "province")
    @Mapping(source = "currentAddress.englishName", target = "currentAddress")
    @Mapping(source = "openingProgram.program.title", target = "program")
    EnrollmentResponse fromEnrollment(Enrollment enrollment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "university", target = "university", qualifiedByName = "toUniversity")
    @Mapping(source = "currentAddress", target = "currentAddress", qualifiedByName = "toCurrentAddress")
    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    void toEnrollmentPartially(EnrollmentRequestUpdate enrollmentRequestUpdate, @MappingTarget Enrollment enrollment);

}
