package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/scholars")
public class ScholarController {

    private final ScholarService scholarService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScholarResponse createScholar(@RequestBody ScholarRequest scholarRequest) {
        return scholarService.createScholar(scholarRequest);
    }
}

