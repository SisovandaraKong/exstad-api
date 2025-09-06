package istad.co.exstadbackendapi.features.openingProgram.learningOutcome;

import istad.co.exstadbackendapi.domain.vo.LearningOutcome;
import istad.co.exstadbackendapi.features.openingProgram.OpeningProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/opening-programs")
public class OPLearningOutcomeController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/learning-outcomes")
    public ResponseEntity<?> setUpLearningOutcomes(@PathVariable String uuid, @RequestBody @Valid List<LearningOutcome> learningOutcome) {
        return new ResponseEntity<>(
                openingProgramService.setUpLearningOutcomes(uuid, learningOutcome), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/learning-outcomes")
    public ResponseEntity<?> getLearningOutcomes(@PathVariable String uuid) {
        return new ResponseEntity<>(
                openingProgramService.getLearningOutcomes(uuid), HttpStatus.OK);
    }
}
