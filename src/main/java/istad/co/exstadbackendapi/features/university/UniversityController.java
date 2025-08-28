package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.university.dto.UniversityRequest;
import istad.co.exstadbackendapi.features.university.dto.UniversityUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/universities")
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    public ResponseEntity<?> getUniversities() {
        return new ResponseEntity<>(
                Map.of("universities",universityService.getAllUniversities()),
                HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUniversityByUuid(@PathVariable String uuid) {
        return new ResponseEntity<>(
                universityService.getUniversityByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUniversity(@RequestBody @Valid UniversityRequest universityRequest){
        return new ResponseEntity<>(
                universityService.createUniversity(universityRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/delete")
    public BasedMessage deleteUniversityByUuid(@PathVariable String uuid) {
        return universityService.deleteUniversityByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateUniversityByUuid(@PathVariable String uuid, @RequestBody @Valid UniversityUpdate universityUpdate) {
        return new ResponseEntity<>(
                universityService.updateUniversityByUuid(uuid, universityUpdate), HttpStatus.OK);
    }
}
