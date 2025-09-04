package istad.co.exstadbackendapi.features.openingProgram.curriculum;

import istad.co.exstadbackendapi.domain.vo.Curriculum;
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
public class OPCurriculumController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/curriculums")
    public ResponseEntity<?> setUpCurriculums(@PathVariable String uuid, @RequestBody @Valid List<Curriculum> curriculums) {
        return new ResponseEntity<>(openingProgramService.setUpCurricula(uuid, curriculums), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/curriculums")
    public ResponseEntity<?> getCurriculums(@PathVariable String uuid) {
        return new ResponseEntity<>(openingProgramService.getCurricula(uuid), HttpStatus.OK);
    }
}
