package istad.co.exstadbackendapi.features.scholar_badge;

import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scholar-badges")
public class ScholarBadgeController {

    private final ScholarBadgeService scholarBadgeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScholarBadgeResponse createBadge(@RequestBody ScholarBadgeRequest scholarBadgeRequest) {
        return scholarBadgeService.createScholarBadge(scholarBadgeRequest);
    }
}
