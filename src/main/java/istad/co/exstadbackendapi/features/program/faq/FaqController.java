package istad.co.exstadbackendapi.features.program.faq;

import istad.co.exstadbackendapi.domain.vo.Faq;
import istad.co.exstadbackendapi.features.program.ProgramService;
import istad.co.exstadbackendapi.features.program.dto.ProgramResponse;
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
