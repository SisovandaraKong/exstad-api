package istad.co.exstadbackendapi.features.openingProgram.detail;

import istad.co.exstadbackendapi.domain.vo.Detail;
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
public class DetailController {
    private final OpeningProgramService openingProgramService;

    @PutMapping("/{uuid}/details")
    public ResponseEntity<?> setUpDetails(@PathVariable String uuid, @RequestBody @Valid List<Detail> details) {
        return new ResponseEntity<>(openingProgramService.setUpDetails(uuid, details), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/details")
    public ResponseEntity<?> getDetails(@PathVariable String uuid) {
        return new ResponseEntity<>(openingProgramService.getDetails(uuid), HttpStatus.OK);
    }
}
