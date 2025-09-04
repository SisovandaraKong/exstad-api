package istad.co.exstadbackendapi.features.openingProgram.timeline;

import istad.co.exstadbackendapi.domain.vo.Timeline;
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
public class TimelineController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/timelines")
    public ResponseEntity<?> setUpTimeline(@PathVariable String uuid, @Valid @RequestBody  List<Timeline> timelines) {
        return new ResponseEntity<>(openingProgramService.setUpTimelines(uuid, timelines), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/timelines")
    public ResponseEntity<?> getTimelines(@PathVariable String uuid) {
        return new ResponseEntity<>(openingProgramService.getTimelines(uuid), HttpStatus.OK);
    }
}
