package istad.co.exstadbackendapi.features.program.learningOutcome;

import istad.co.exstadbackendapi.domain.vo.LearningOutcome;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class LearningOutcomeController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/learning-outcomes")
    public ResponseEntity<ProgramResponse> updateLearningOutcomes(
            @PathVariable String uuid,
            @RequestBody @Valid List<@Valid LearningOutcome> learningOutcomes
    ) {
        return ResponseEntity.ok(programService.setUpLearningOutcomes(uuid, learningOutcomes));
    }

    @GetMapping("/{uuid}/learning-outcomes")
    public ResponseEntity<List<LearningOutcome>> getLearningOutcomes(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getLearningOutcomes(uuid));
    }
}
