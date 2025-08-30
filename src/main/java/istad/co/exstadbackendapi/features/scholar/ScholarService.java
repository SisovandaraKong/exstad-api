package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;

public interface ScholarService {
    ScholarResponse createScholar(ScholarRequest scholarRequest);
}
