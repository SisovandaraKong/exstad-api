package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.domain.University;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequestUpdate;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import istad.co.exstadbackendapi.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    @Override
    public UniversityResponse getUniversityByUuid(String uuid) {
        University university =  universityRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));


        return universityMapper.fromUniversity(university);
    }

    @Override
    public List<UniversityResponse> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream().map(
                universityMapper::fromUniversity
        ).toList();
    }

    @Override
    public UniversityResponse createUniversity(UniversityRequest universityRequest) {
        if(universityRepository.existsByEnglishName(universityRequest.englishName())
        || universityRepository.existsByKhmerName((universityRequest.khmerName())) ||
         universityRepository.existsByShortName(universityRequest.shortName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "University name already exists");
        }

        University university = universityMapper.toUniversity(universityRequest);
        university.setUuid(UUID.randomUUID().toString());
        return universityMapper.fromUniversity(
                universityRepository.save(university)
        );
    }

    @Override
    public UniversityResponse updateUniversity(String uuid, UniversityRequestUpdate universityRequest) {
        University university = universityRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));
        if(universityRepository.existsByEnglishName(universityRequest.englishName())
                || universityRepository.existsByKhmerName((universityRequest.khmerName())) ||
                universityRepository.existsByShortName(universityRequest.shortName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "University name already exists");
        }
        universityMapper.toUniversityPartially(university, universityRequest);
        return universityMapper.fromUniversity(universityRepository.save(university));
    }
}
