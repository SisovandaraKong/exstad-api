package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequestUpdate;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/scholars")
public class ScholarController {

    private final ScholarService scholarService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllScholars() {
        return new ResponseEntity<>(
                Map.of("scholars", scholarService.findAllScholars()), HttpStatus.OK);
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
    public ResponseEntity<?> getScholarCount() {
        return new ResponseEntity<>(Map.of("scholars", scholarService.countScholars()), HttpStatus.OK);
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
    @PutMapping("/{uuid}/soft-delete")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage softDeleteScholar(@PathVariable String uuid) {
        return scholarService.softDeleteScholarByUuid(uuid);
    }

    @PutMapping("/{uuid}/restore")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage restoreScholar(@PathVariable String uuid) {
        return scholarService.restoreScholarByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage hardDeleteScholar(@PathVariable String uuid) {
        return scholarService.hardDeleteScholarByUuid(uuid);
    }
}

