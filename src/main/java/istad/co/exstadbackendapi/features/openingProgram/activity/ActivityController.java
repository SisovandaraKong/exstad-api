package istad.co.exstadbackendapi.features.openingProgram.activity;

import istad.co.exstadbackendapi.domain.vo.Activity;
import istad.co.exstadbackendapi.features.openingProgram.OpeningProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/opening-programs")
public class ActivityController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/activities")
    public ResponseEntity<?> setUpActivity(@PathVariable String uuid, @RequestBody @Valid List<Activity> activities) {
        return ResponseEntity.ok(openingProgramService.setUpActivities(uuid, activities));
    }

    @GetMapping("/{uuid}/activities")
    public ResponseEntity<?> getActivities(@PathVariable String uuid) {
        return ResponseEntity.ok(openingProgramService.getActivities(uuid));
    }
}
