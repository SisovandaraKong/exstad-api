package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.domain.Certificate;
import co.istad.exstadapi.domain.OpeningProgram;
import co.istad.exstadapi.domain.Scholar;
import co.istad.exstadapi.features.certificate.dto.CertificateResponse;
import co.istad.exstadapi.features.certificate.dto.CertificateRequestDto;
import co.istad.exstadapi.features.document.DocumentService;
import co.istad.exstadapi.features.document.dto.DocumentResponse;
import co.istad.exstadapi.features.openingProgram.OpeningProgramRepository;
import co.istad.exstadapi.features.scholar.ScholarRepository;
import co.istad.exstadapi.mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateServiceImpl implements CertificateService {

    private final DocumentService documentService;
    private final ScholarRepository scholarRepository;
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private final OpeningProgramRepository openingProgramRepository;


    @Override
    public CertificateResponse generateCertificate(String offeringType,CertificateRequestDto request) {
        try {
            if (request.scholarUuid() == null || request.scholarUuid().isEmpty()) {
                throw new IllegalArgumentException("At least one student name is required.");
            }
            if (request.bgImage() == null || request.bgImage().trim().isEmpty()) {
                throw new IllegalArgumentException("Template filename is required.");
            }

            InputStream reportStream = getClass().getResourceAsStream("/generates/certificate.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            OpeningProgram openingProgram = openingProgramRepository.findByUuid(request.openingProgramUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));

            Optional<Scholar> scholar = scholarRepository.findByUuid(request.scholarUuid());

            // Prepare parameters (data) for JasperReports
            Map<String, Object> parameters = new HashMap<>();
            if(scholar.get().getUser().getGender().equals("Male")){
                parameters.put("studentName", "Mr. "+scholar.get().getUser().getEnglishName().toUpperCase());
            }
            else if(scholar.get().getUser().getGender().equals("Female")){
                parameters.put("studentName", "Ms. "+scholar.get().getUser().getEnglishName().toUpperCase());
            }
            parameters.put("bgImage", request.bgImage());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    new JREmptyDataSource()
            );

            byte[]  pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Upload PDF to MinIO
            String fileName = "certificate.pdf";
            MultipartFile multipartFile = new InMemoryMultipartFile(
                    "file",               // form field name
                    fileName,             // original filename
                    "application/pdf",    // content type
                    pdfBytes              // content
            );

            // upload certificate to minio
            DocumentResponse documentResponse = documentService.uploadDocument(
                    offeringType,
                    openingProgram.getGeneration(),
                    "certificate",
                    "null",
                    multipartFile
            );

            // Save certificate info to database
            Certificate certificate = new Certificate();
            certificate.setScholar(scholar.orElse(null));
            certificate.setOpeningProgram(openingProgram);
            certificate.setTempCertificateUrl(documentResponse.uri());
            certificate.setIsDisabled(false);
            certificate.setIsDeleted(false);
            certificate.setIsVerified(false);
            certificate.setVerifiedAt(null);
            // Set certificateUrl to null beacause it's not verified yet
            certificate.setCertificateUrl(null);
            certificateRepository.save(certificate);

            return certificateMapper.toCertificateResponse(certificate);
        } catch (Exception e) {
            log.error("Certificate generation failed", e);
            throw new RuntimeException("Failed to generate certificate", e);
        }
    }

    @Override
    public CertificateResponse verifyCertificate(String offeringType, MultipartFile file, String openingProgramUuid, String scholarUuid) {

        OpeningProgram openingProgram = openingProgramRepository.findByUuid(openingProgramUuid)
                .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));

        DocumentResponse documentResponse = documentService.uploadDocument(offeringType, openingProgram.getGeneration(), "certificate", "null", file);
        Scholar scholar = scholarRepository.
                findByUuid(scholarUuid).orElseThrow(() -> new IllegalArgumentException("Scholar not found"));
        Optional<Certificate> certificate = certificateRepository.findByScholarAndOpeningProgram(scholar, openingProgram);

        if(certificate.isPresent()){
            certificate.get().setIsVerified(true);
            certificate.get().setVerifiedAt(LocalDate.now());
            certificate.get().setCertificateUrl(documentResponse.uri());
            certificateRepository.save(certificate.get());
            return certificateMapper.toCertificateResponse(certificate.get());
        }
        return null;
    }

    @Override
    public List<CertificateResponse> getAllCertificates() {
        List<Certificate> certificates = certificateRepository.findAll();
        return certificates.stream().map(certificateMapper::toCertificateResponse).toList();
    }

    @Override
    public CertificateResponse getCertificateByScholarAndOpeningProgram(String scholarUuid, String openingProgramUuid) {
        Scholar scholar = scholarRepository.
                findByUuid(scholarUuid).orElseThrow(() -> new IllegalArgumentException("Scholar not found"));
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(openingProgramUuid)
                .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));

        Certificate certificates = certificateRepository.findByScholarAndOpeningProgram(scholar, openingProgram)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Certificate not found"));
        return certificateMapper.toCertificateResponse(certificates);
    }

    @Override
    public BasedMessage deleteCertificateByScholarAndOpeningProgram(String scholarUuid, String openingProgramUuid) {
        Scholar scholar = scholarRepository.
                findByUuid(scholarUuid).orElseThrow(() -> new IllegalArgumentException("Scholar not found"));
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(openingProgramUuid)
                .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));

        Certificate certificates = certificateRepository.findByScholarAndOpeningProgram(scholar, openingProgram)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Certificate not found"));

        certificates.setIsDeleted(true);
        certificateRepository.save(certificates);
        return new BasedMessage("Certificate deleted successfully");
    }

}