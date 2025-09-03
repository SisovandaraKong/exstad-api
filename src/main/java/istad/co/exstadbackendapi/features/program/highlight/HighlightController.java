package istad.co.exstadbackendapi.features.program.highlight;

import istad.co.exstadbackendapi.domain.vo.Highlight;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class HighlightController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/highlights")
    public ResponseEntity<ProgramResponse> updateHighlights(
            @PathVariable String uuid,
            @RequestBody List<Highlight> highlights
    ) {
        return ResponseEntity.ok(programService.setUpHighlights(uuid, highlights));
    }

    @GetMapping("/{uuid}/highlights")
    public ResponseEntity<List<Highlight>> getHighlights(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getHighlights(uuid));
    }
}
