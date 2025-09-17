package co.istad.exstadapi.features.openingProgram.curriculum;

import co.istad.exstadapi.domain.vo.Curriculum;
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
public class OPCurriculumController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/curriculums")
    public ResponseEntity<?> setUpCurriculums(@PathVariable String uuid, @RequestBody @Valid List<Curriculum> curricula) {
        return new ResponseEntity<>(openingProgramService.setUpCurricula(uuid, curricula), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/curriculums")
    public ResponseEntity<?> getCurriculums(@PathVariable String uuid) {
        return new ResponseEntity<>(openingProgramService.getCurricula(uuid), HttpStatus.OK);
    }
}
