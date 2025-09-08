package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.features.certificate.dto.CertificateRequestDto;
import co.istad.exstadapi.features.document.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class CertificateController {


    private final CertificateService certificateService;
    private final DocumentService documentService;

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
}
