package co.istad.exstadapi.features.enrollment;

import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequest;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequestUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createEnrollment(@RequestBody @Valid EnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enrollmentService.createEnrollment(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllEnrollments() {
        return new ResponseEntity<>(Map.of("enrollments",enrollmentService.getAllEnrollments()), HttpStatus.OK);
    }

    @GetMapping("/interviewed")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllInterviewedEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllInterviewedEnrollments());
    }

    @GetMapping("/achieved")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAchievedEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllAchievedEnrollments());
    }

    @GetMapping("/passed")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllPassedEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllPassedEnrollments());
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEnrollment(@PathVariable String uuid) {
        return ResponseEntity.ok(enrollmentService.getEnrollment(uuid));
    }

    @PatchMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateEnrollment(
            @PathVariable String uuid,
            @RequestBody EnrollmentRequestUpdate requestUpdate) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(uuid, requestUpdate));
    }


}
