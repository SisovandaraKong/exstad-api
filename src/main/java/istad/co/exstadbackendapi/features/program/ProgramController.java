package istad.co.exstadbackendapi.features.program;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/programs")
public class ProgramController {
    private final ProgramService programService;

    @GetMapping
    public ResponseEntity<?> getAllPrograms() {
        return new ResponseEntity<>(
                Map.of("programs",programService.getAllPrograms()),
                HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProgramByUuid(@PathVariable String uuid) {
        return new ResponseEntity<>(
                programService.getProgramByUuid(uuid), HttpStatus.OK);
    }
}
