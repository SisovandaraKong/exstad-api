package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.University;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import istad.co.exstadbackendapi.features.university.dto.UniversityUpdate;
import istad.co.exstadbackendapi.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    @Override
    public List<UniversityResponse> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities
                .stream()
                .filter(university -> university.getIsDeleted().equals(false))
                .map(universityMapper::toUniversityResponse)
                .toList();
    }

    @Override
    public UniversityResponse getUniversityByUuid(String uuid) {
        University university = universityRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));
        return universityMapper.toUniversityResponse(university);
    }

    @Override
    public UniversityResponse createUniversity(UniversityRequest universityRequest) {
        if (universityRepository.existsByEnglishName(universityRequest.englishName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "English name is already taken");
        }
        if (universityRepository.existsByKhmerName(universityRequest.khmerName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Khmer name is already taken");
        }

        University university = new University();
        university.setUuid(UUID.randomUUID().toString());
        university.setEnglishName(universityRequest.englishName());
        university.setKhmerName(universityRequest.khmerName());
        university.setShortName(universityRequest.shortName());
        university.setIsDeleted(false);
        university = universityRepository.save(university);
        return universityMapper.toUniversityResponse(university);
    }

    @Transactional
    @Override
    public BasedMessage deleteUniversityByUuid(String uuid) {
        University university = universityRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found")
        );
        university.setIsDeleted(true);
        universityRepository.save(university);
        return new BasedMessage("University deleted successfully");
    }

    @Override
    public UniversityResponse updateUniversityByUuid(String uuid, UniversityUpdate universityUpdate) {
        University university = universityRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));
        universityMapper.updateUniversityFromDto(universityUpdate, university);
        university = universityRepository.save(university);
        return universityMapper.toUniversityResponse(university);
    }
}
