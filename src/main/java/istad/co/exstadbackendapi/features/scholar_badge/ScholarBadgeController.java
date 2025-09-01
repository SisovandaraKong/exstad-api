package istad.co.exstadbackendapi.features.scholar_badge;

import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequestUpdate;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ScholarBadgeResponse getByUuid(@PathVariable String uuid) {
        return scholarBadgeService.findByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ScholarBadgeResponse updateByUuid(@PathVariable String uuid, @RequestBody @Valid ScholarBadgeRequestUpdate updateBadgeRequest) {
        return scholarBadgeService.updateScholarBadgeByUuid(uuid, updateBadgeRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@PathVariable String uuid) {
        scholarBadgeService.deleteScholarBadge(uuid);
    }
}
