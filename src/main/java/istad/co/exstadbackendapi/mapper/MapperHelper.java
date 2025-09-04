package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.*;
import istad.co.exstadbackendapi.features.badge.BadgeRepository;
import istad.co.exstadbackendapi.features.currentAddress.CurrentAddressRepository;
import istad.co.exstadbackendapi.features.openingProgram.OpeningProgramRepository;
import istad.co.exstadbackendapi.features.program.ProgramRepository;
import istad.co.exstadbackendapi.features.province.ProvinceRepository;
import istad.co.exstadbackendapi.features.scholar.ScholarRepository;
import istad.co.exstadbackendapi.features.university.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final ScholarRepository scholarRepository;
    private final BadgeRepository badgeRepository;
    private final UniversityRepository universityRepository;
    private final ProvinceRepository provinceRepository;
    private final CurrentAddressRepository currentAddressRepository;
    private final OpeningProgramRepository openingProgramRepository;
    private final ProgramRepository programRepository;

    @Named("toUniversity")
    public University toUniversity(final String university) {
        return universityRepository.findByEnglishName(university).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"University not found")
        );
    }

    @Named("toProvince")
    public Province toProvince(final String province) {
        return provinceRepository.findByEnglishName(province).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Province not found")
        );
    }

    @Named("toCurrentAddress")
    public CurrentAddress toCurrentAddress(final String currentAddress) {
        return currentAddressRepository.findByEnglishName(currentAddress).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Current address not found")
        );
    }

    @Named("toScholar")
    public Scholar toScholar(final String scholarUuid) {
        return scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Scholar not found")
        );
    }

    @Named("toBadge")
    public Badge toBadge(final String badgeUuid) {
        return badgeRepository.findByUuid(badgeUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Badge not found")
        );
    }

    @Named("toOpeningProgramTitle")
    public Program toOpeningProgramTitle(final String uuid) {
        return programRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Program not found")
        );
    }

}
