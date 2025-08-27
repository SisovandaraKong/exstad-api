package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import istad.co.exstadbackendapi.features.university.dto.UniversityUpdate;

import java.util.List;

public interface UniversityService {
    List<UniversityResponse> getAllUniversities();
    UniversityResponse getUniversityByUuid(String uuid);
    UniversityResponse createUniversity(UniversityRequest universityRequest);
    BasedMessage deleteUniversityByUuid(String uuid);
    UniversityResponse updateUniversityByUuid(String uuid, UniversityUpdate universityUpdate);
}
