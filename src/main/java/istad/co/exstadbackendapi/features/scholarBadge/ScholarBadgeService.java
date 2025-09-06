package istad.co.exstadbackendapi.features.scholarBadge;

import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeRequestUpdate;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeResponse;

public interface ScholarBadgeService {

    ScholarBadgeResponse createScholarBadge(ScholarBadgeRequest scholarBadgeRequest);
    ScholarBadgeResponse findByUuid(String uuid);
    ScholarBadgeResponse updateScholarBadgeByUuid(String uuid, ScholarBadgeRequestUpdate scholarBadgeRequestUpdate);
    void deleteScholarBadge(String uuid);
}
