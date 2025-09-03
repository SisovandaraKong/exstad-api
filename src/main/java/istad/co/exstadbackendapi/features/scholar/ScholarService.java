package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequestUpdate;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;

import java.util.List;

public interface ScholarService {

    ScholarResponse createScholar(ScholarRequest scholarRequest);
    ScholarResponse updateScholar(String uuid, ScholarRequestUpdate scholarRequestUpdate);
    List<ScholarResponse> createMultipleScholars(List<ScholarRequest> scholarRequests);

    List<ScholarResponse> findAllScholars();
    ScholarResponse findByUuid(String uuid);
    ScholarResponse findByUsername(String username);
    List<ScholarResponse> searchByEnglishName(String englishName);
    List<ScholarResponse> searchByUsername(String username);

    Long countScholars();

    BasedMessage softDeleteScholarByUuid(String uuid);
    BasedMessage restoreScholarByUuid(String uuid);
    BasedMessage hardDeleteScholarByUuid(String uuid);
}
