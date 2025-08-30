package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.CurrentAddress;
import istad.co.exstadbackendapi.domain.Province;
import istad.co.exstadbackendapi.domain.University;
import istad.co.exstadbackendapi.features.current_address.CurrentAddressRepository;
import istad.co.exstadbackendapi.features.province.ProvinceRepository;
import istad.co.exstadbackendapi.features.university.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final UniversityRepository universityRepository;
    private final ProvinceRepository provinceRepository;
    private final CurrentAddressRepository currentAddressRepository;

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

}
