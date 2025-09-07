package istad.co.exstadbackendapi.features.program.requirement;

import istad.co.exstadbackendapi.domain.vo.Requirement;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class RequirementController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/requirements")
    public ResponseEntity<ProgramResponse> updateRequirements(
            @PathVariable String uuid,
            @RequestBody List<Requirement> requirements
    ) {
        return ResponseEntity.ok(programService.setUpRequirements(uuid, requirements));
    }

    @GetMapping("/{uuid}/requirements")
    public ResponseEntity<List<Requirement>> getRequirements(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getRequirements(uuid));
    }
}
