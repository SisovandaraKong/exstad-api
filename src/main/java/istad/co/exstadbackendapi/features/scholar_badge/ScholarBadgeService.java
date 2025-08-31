package istad.co.exstadbackendapi.features.scholar_badge;

import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;

public interface ScholarBadgeService {

    ScholarBadgeResponse createScholarBadge(ScholarBadgeRequest scholarBadgeRequest);

}
