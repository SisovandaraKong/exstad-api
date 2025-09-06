package istad.co.exstadbackendapi.features.openingProgram.requirement;

import istad.co.exstadbackendapi.domain.vo.Requirement;
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
public class OPRequirementController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/requirements")
    public ResponseEntity<?> setUpRequirements(@PathVariable String uuid, @RequestBody @Valid List<Requirement> requirements) {
        return new ResponseEntity<>(
                openingProgramService.setUpRequirements(uuid, requirements), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/requirements")
    public ResponseEntity<?> getRequirements(@PathVariable String uuid) {
        return new ResponseEntity<>(
                openingProgramService.getRequirements(uuid), HttpStatus.OK);
    }
}
