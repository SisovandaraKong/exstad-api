package co.istad.exstadapi.features.scholar;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.enums.ScholarStatus;
import co.istad.exstadapi.features.scholar.dto.*;
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

    @GetMapping("/abroad")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAbroadScholars() {
        return new ResponseEntity<>(
                Map.of("scholars", scholarService.getAllAbroadScholars()), HttpStatus.OK);
    }

    @GetMapping("/status/{scholarStatus}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllScholarsByStatus(@PathVariable("scholarStatus") String scholarStatus) {
        return new ResponseEntity<>(
                Map.of("scholars", scholarService.getAllScholarsByStatus(ScholarStatus.valueOf(scholarStatus.trim().toUpperCase()))), HttpStatus.OK
        );
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

    @PutMapping("/set-major/{uuid}")
    public ScholarResponse setMajorToAlumniScholar(@PathVariable String uuid, @RequestBody @Valid SetMajorToAlumniScholar setMajorToAlumniScholar){
        return scholarService.setMajorToAlumniScholar(setMajorToAlumniScholar, uuid);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ScholarResponse getMe() {
        return scholarService.getCurrentScholar();
    }

    @PatchMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ScholarResponse updateMe(@RequestBody @Valid ScholarRequestUpdate scholarRequestUpdate) {
        return scholarService.updateCurrentScholar(scholarRequestUpdate);
    }


    @GetMapping("/{uuid}/social-links")
    @ResponseStatus(HttpStatus.OK)
    public List<SocialLinkResponse> getScholarSocialLinks(@PathVariable String uuid) {
        return scholarService.getScholarSocialLink(uuid);
    }

    @PostMapping("/{uuid}/social-links")
    @ResponseStatus(HttpStatus.CREATED)
    public SocialLinkResponse addScholarSocialLink(@PathVariable String uuid, @RequestBody @Valid SocialLinkRequest socialLinkRequest) {
        return scholarService.setUpScholarSocialLink(uuid, socialLinkRequest);
    }

    @PatchMapping("/{scholarUuid}/social-link/{socialLinkUuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SocialLinkResponse updateSocialLinkStatus(@PathVariable String scholarUuid, @PathVariable String socialLinkUuid, @RequestBody Boolean status) {
        return scholarService.updateSocialLinkStatus(scholarUuid, socialLinkUuid, status);
    }

    @DeleteMapping("/{scholarUuid}/social-link/{socialLinkUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BasedMessage deleteSocialLink(@PathVariable String scholarUuid, @PathVariable String socialLinkUuid) {
        scholarService.deleteSocialLink(scholarUuid, socialLinkUuid);
        return new BasedMessage("Scholar deleted successfully");
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

    @GetMapping("/{uuid}/opening-program")
    public ResponseEntity<?> getAllScholarsByOpeningProgramUuid(@PathVariable String uuid) {
        return new ResponseEntity<>(
                Map.of("opening-program-scholars", scholarService.getAllScholarsByOpeningProgramUuid(uuid)), HttpStatus.OK);
    }
}

