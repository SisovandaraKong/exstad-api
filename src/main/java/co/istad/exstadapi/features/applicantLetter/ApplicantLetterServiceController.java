package co.istad.exstadapi.features.applicantLetter;


import co.istad.exstadapi.features.applicantLetter.dto.ApplicantLetterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ApplicantLetterServiceController {
    private final ApplicantLetterService applicantLetterService;

    @GetMapping("/generate-applicant-letter")
    public ResponseEntity<?> generateApplicantLetter(@RequestBody @Valid ApplicantLetterRequest applicantLetterRequest) {
        try{
            byte[] pdfBytes = applicantLetterService.generateApplicantLetter(applicantLetterRequest);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=certificate.pdf")
                    .body(pdfBytes);
        }catch(Exception e){
            log.error("Applicant letter generation failed", e);
            return ResponseEntity.internalServerError().body("Failed to generate applicant letter");
        }
    }

    @PostMapping("/letters/download-zip")
    public ResponseEntity<Resource> downloadApplicantLettersZip(@RequestBody List<ApplicantLetterRequest> requests) {
        byte[] zipBytes = applicantLetterService.generateApplicantLettersZip(requests);
        ByteArrayResource resource = new ByteArrayResource(zipBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=applicant_letters.zip")
                .contentLength(zipBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
