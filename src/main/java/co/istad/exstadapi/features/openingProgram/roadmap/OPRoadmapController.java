package co.istad.exstadapi.features.openingProgram.roadmap;

import co.istad.exstadapi.domain.vo.Roadmap;
import co.istad.exstadapi.features.openingProgram.OpeningProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/opening-programs")
public class OPRoadmapController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/roadmaps")
    public ResponseEntity<?> setUpRoadmaps(@PathVariable String uuid, @RequestBody @Valid List<Roadmap> roadmaps) {
        return new ResponseEntity<>(openingProgramService.setUpRoadmaps(uuid, roadmaps), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/roadmaps")
    public ResponseEntity<?> getRoadmaps(@PathVariable String uuid) {
        return new ResponseEntity<>(openingProgramService.getRoadmaps(uuid), HttpStatus.OK);
    }
}
