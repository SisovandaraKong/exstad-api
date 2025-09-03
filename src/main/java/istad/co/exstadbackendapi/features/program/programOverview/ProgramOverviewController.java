package istad.co.exstadbackendapi.features.program.programOverview;

import istad.co.exstadbackendapi.domain.vo.ProgramOverview;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class ProgramOverviewController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/program-overviews")
    public ResponseEntity<ProgramResponse> updateProgramOverviews(
            @PathVariable String uuid,
            @RequestBody List<ProgramOverview> overviews
    ) {
        return ResponseEntity.ok(programService.setUpProgramOverviews(uuid, overviews));
    }

    @GetMapping("/{uuid}/program-overviews")
    public ResponseEntity<List<ProgramOverview>> getProgramOverviews(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getProgramOverviews(uuid));
    }

}
