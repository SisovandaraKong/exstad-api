package istad.co.exstadbackendapi.features.scholar_badge;

import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequestUpdate;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;

public interface ScholarBadgeService {

    ScholarBadgeResponse createScholarBadge(ScholarBadgeRequest scholarBadgeRequest);
    ScholarBadgeResponse findByUuid(String uuid);
    ScholarBadgeResponse updateScholarBadgeByUuid(String uuid, ScholarBadgeRequestUpdate scholarBadgeRequestUpdate);
    void deleteScholarBadge(String uuid);
}
