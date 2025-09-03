package istad.co.exstadbackendapi.features.program.timeline;

import istad.co.exstadbackendapi.domain.vo.Timeline;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class TimelineController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/timelines")
    public ResponseEntity<ProgramResponse> updateTimelines(
            @PathVariable String uuid,
            @RequestBody List<Timeline> timelines
    ) {
        return ResponseEntity.ok(programService.setUpTimelines(uuid, timelines));
    }

    @GetMapping("/{uuid}/timelines")
    public ResponseEntity<List<Timeline>> getTimelines(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getTimelines(uuid));
    }
}
