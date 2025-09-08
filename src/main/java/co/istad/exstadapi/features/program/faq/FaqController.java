package co.istad.exstadapi.features.program.faq;

import co.istad.exstadapi.domain.vo.Faq;
import co.istad.exstadapi.features.program.ProgramService;
import co.istad.exstadapi.features.program.dto.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class FaqController {
    private final ProgramService programService;

    @PutMapping("/{uuid}/faqs")
    public ResponseEntity<ProgramResponse> updateFaqs(
            @PathVariable String uuid,
            @RequestBody List<Faq> faqs
    ) {
        return ResponseEntity.ok(programService.setUpFaqs(uuid, faqs));
    }

    @GetMapping("/{uuid}/faqs")
    public ResponseEntity<List<Faq>> getFaqs(@PathVariable String uuid) {
        return ResponseEntity.ok(programService.getFaqs(uuid));
    }
}
