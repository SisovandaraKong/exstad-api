package co.istad.exstadapi.features.program.roadmap;

import co.istad.exstadapi.domain.vo.Roadmap;
import co.istad.exstadapi.features.program.ProgramService;
import co.istad.exstadapi.features.program.dto.ProgramResponse;
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
