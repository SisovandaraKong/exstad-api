package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequestUpdate;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/scholars")
public class ScholarController {

    private final ScholarService scholarService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ScholarResponse> getAllScholars() {
        return scholarService.findAllScholars();
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ScholarResponse getScholarByUuid(@PathVariable String uuid) {
        return scholarService.findByUuid(uuid);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ScholarResponse getScholarByUsername(@PathVariable String username) {
        return scholarService.findByUsername(username);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ScholarResponse> getScholarsByName(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String name) {
        if (!(name == null || name.isEmpty())) {
            return scholarService.searchByEnglishName(name);
        }else if(!(username == null || username.isEmpty())){
            return scholarService.searchByUsername(username);
        }
        return List.of();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public long getScholarCount() {
        return scholarService.countScholars();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScholarResponse createScholar(@RequestBody @Valid ScholarRequest scholarRequest) {
        return scholarService.createScholar(scholarRequest);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ScholarResponse> createMultipleScholars(@RequestBody @Valid List<ScholarRequest> scholarRequests) {
        return scholarService.createMultipleScholars(scholarRequests);
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ScholarResponse updateScholar(@PathVariable String uuid, @RequestBody @Valid ScholarRequestUpdate scholarRequestUpdate){
        return scholarService.updateScholar(uuid, scholarRequestUpdate);
    }
}

