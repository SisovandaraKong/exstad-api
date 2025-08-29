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
@CrossOrigin("http://localhost:3000")
public class UniversityController {

    private final UniversityService universityService;
    @GetMapping
    public ResponseEntity<?> getUniversities() {
        return new ResponseEntity<>(
                universityService.getAllUniversities(), HttpStatus.OK);
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



}
