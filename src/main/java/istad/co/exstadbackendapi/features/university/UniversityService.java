package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequestUpdate;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;

import java.util.List;

public interface UniversityService {
    UniversityResponse getUniversityByUuid(String uuid);
    List<UniversityResponse> getAllUniversities();
    UniversityResponse createUniversity(UniversityRequest universityRequest);
    UniversityResponse updateUniversity(String uuid, UniversityRequestUpdate universityRequest);
}
