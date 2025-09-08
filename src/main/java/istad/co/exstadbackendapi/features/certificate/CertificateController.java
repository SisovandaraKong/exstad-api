package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateResponse;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateRequestDto;
import istad.co.exstadbackendapi.features.document.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class CertificateController {


    private final CertificateService certificateService;

    @PostMapping("/generate-certificates/{offeringType}")
    public ResponseEntity<?> generateCertificate(@PathVariable String offeringType, @RequestBody CertificateRequestDto request) {
        try {
//            byte[] pdfBytes = certificateService.generateCertificate(request);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=certificate.pdf")
//                    .body(pdfBytes);
            return ResponseEntity.ok(certificateService.generateCertificate(offeringType, request));
        } catch (Exception e) {
            log.error("Certificate generation failed", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate certificate", e);
        }
    }

    @PostMapping("/verify-certificates/{offeringType}/{openingProgramUuid}/{scholarUuid}")
    public ResponseEntity<?> verifyCertificate(
            @PathVariable String offeringType,
            @RequestPart("file") MultipartFile file,
            @PathVariable String openingProgramUuid,
            @PathVariable String scholarUuid)
    {
        return new ResponseEntity<>(certificateService.verifyCertificate(offeringType , file ,openingProgramUuid, scholarUuid), HttpStatus.OK);
    }

    @GetMapping("/certificates/{scholarUuid}/opening-program/{openingProgramUuid}")
    public CertificateResponse getCertificateByScholar(@PathVariable String scholarUuid,@PathVariable String openingProgramUuid){
        return certificateService.getCertificateByScholarAndOpeningProgram(scholarUuid, openingProgramUuid);
    }

    @PutMapping("/certificates/{scholarUuid}/opening-program/{openingProgramUuid}/delete")
    public BasedMessage deleteCertificateByScholar(@PathVariable String scholarUuid, @PathVariable String openingProgramUuid){
        return certificateService.deleteCertificateByScholarAndOpeningProgram(scholarUuid, openingProgramUuid);
    }

    @GetMapping("/certificates")
    public ResponseEntity<?> getAllCertificates(){
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }
}
