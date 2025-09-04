package istad.co.exstadbackendapi.features.program.roadmap;

import istad.co.exstadbackendapi.domain.vo.Roadmap;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class RoadmapController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/roadmaps")
    public ResponseEntity<ProgramResponse> updateRoadmaps(
            @PathVariable String uuid,
            @RequestBody List<Roadmap> roadmaps
    ) {
        return ResponseEntity.ok(programService.setUpRoadmaps(uuid, roadmaps));
    }

    @GetMapping("/{uuid}/roadmaps")
    public ResponseEntity<List<Roadmap>> getRoadmaps(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getRoadmaps(uuid));
    }
}
