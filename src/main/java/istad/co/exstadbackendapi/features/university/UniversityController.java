package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequestUpdate;
import istad.co.exstadbackendapi.features.university.dto.UniversityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/universities")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;
    @GetMapping
    public List<UniversityResponse> getUniversities() {
        return universityService.getAllUniversities();
    }

    @PostMapping
    public UniversityResponse createUniversity(@RequestBody UniversityRequest universityRequest) {
        return universityService.createUniversity(universityRequest);
    }

    @PatchMapping("{uuid}")
    public UniversityResponse updateUniversity(@PathVariable String uuid, @RequestBody UniversityRequestUpdate universityRequest) {
        return universityService.updateUniversity(uuid, universityRequest);
    }



}
