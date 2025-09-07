package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.features.certificate.dto.CertificateResponse;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateRequestDto;
import istad.co.exstadbackendapi.features.document.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificates")
@Slf4j
public class CertificateController {


    private final CertificateService certificateService;
    private final DocumentService documentService;

    @PostMapping("/generate")
    public ResponseEntity<CertificateResponse> generateCertificate(@RequestBody CertificateRequestDto request) {
        try {
//            byte[] pdfBytes = certificateService.generateCertificate(request);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=certificate.pdf")
//                    .body(pdfBytes);
            return ResponseEntity.ok(certificateService.generateCertificate(request));
        } catch (Exception e) {
            log.error("Certificate generation failed", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate certificate", e);
        }
    }
}
