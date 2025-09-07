package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.domain.Certificate;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateResponse;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateRequestDto;
import istad.co.exstadbackendapi.features.document.DocumentService;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import istad.co.exstadbackendapi.features.scholar.ScholarRepository;
import istad.co.exstadbackendapi.mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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


    @Override
    public CertificateResponse generateCertificate(CertificateRequestDto request) {
        try {
            if (request.scholarUuids() == null || request.scholarUuids().isEmpty()) {
                throw new IllegalArgumentException("At least one student name is required.");
            }
            if (request.bgImage() == null || request.bgImage().trim().isEmpty()) {
                throw new IllegalArgumentException("Template filename is required.");
            }

            InputStream reportStream = getClass().getResourceAsStream("/generates/certificate.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            List<JasperPrint> jasperPrintList = new ArrayList<>();

            Certificate certificate = new Certificate();
            for (String scholarUuid : request.scholarUuids()) {
                if (scholarUuid == null || scholarUuid.trim().isEmpty()) {
                    continue;
                }

                Optional<Scholar> scholar = scholarRepository.findByUuid(scholarUuid);
                certificate.setScholar(scholar.orElse(null));
                certificate.setOpeningProgram(null);
                certificate.setCertificateUrl(null);
                // save null uri for each scholar first beacause this uri will store all scholar's certificate
//                certificate.setTempCertificateUrl(null);
                certificate.setIsDisabled(false);
                certificate.setIsDeleted(false);
                certificate.setIsVerified(false);
                certificate.setVerifiedAt(null);
                certificateRepository.save(certificate);

                if(scholar.get().getUser().getGender().equals("Male")){
                    scholar.get().getUser().setEnglishName("Mr. "+scholar.get().getUser().getEnglishName());
                }
                else if(scholar.get().getUser().getGender().equals("Female")){
                    scholar.get().getUser().setEnglishName("Ms. "+scholar.get().getUser().getEnglishName());
                }

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("studentName", scholar.get().getUser().getEnglishName());
                parameters.put("bgImage", request.bgImage());

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        new JREmptyDataSource()
                );

                jasperPrintList.add(jasperPrint);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setCreatingBatchModeBookmarks(true);
            exporter.setConfiguration(configuration);

            exporter.exportReport();

            byte[] pdfBytes = outputStream.toByteArray();

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
                    "fswd",  // I will use request.openingProgram() to fine offering type later
                    1,  // This one also
                    "certificate",
                    fileName,
                    multipartFile
            );

            certificate.setCertificateUrl(documentResponse.uri());

            // this will save uri for all scholars
//            certificateRepository.save(certificate);

            return certificateMapper.toCertificateResponse(certificate);
        } catch (Exception e) {
            log.error("Certificate generation failed", e);
            throw new RuntimeException("Failed to generate certificate", e);
        }
    }

    @Override
    public CertificateResponse verifyCertificate(String offeringType, int gen, String documentType, String preferredFileName, MultipartFile file, Integer openingProgramId, String uuid) {
        DocumentResponse documentResponse = documentService.uploadDocument(offeringType, gen, documentType, preferredFileName, file);
        Scholar scholar = scholarRepository.
                findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("Scholar not found"));
        // This will be used later when opening program is ready
//        OpeningProgram openingProgram = openingProgramRepository.findById(openingProgramId)
//                .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));
//        Optional<Certificate> certificate = certificateRepository.findByScholarAndOpeningProgram(scholar, openingProgram );
        Optional<Certificate> certificate = certificateRepository.findByScholar(scholar);
        if(certificate.isPresent()){
            certificate.get().setIsVerified(true);
            certificate.get().setVerifiedAt(LocalDate.now());
            certificate.get().setCertificateUrl(documentResponse.uri());
            certificateRepository.save(certificate.get());
            return certificateMapper.toCertificateResponse(certificate.get());
        }
        return null;
    }
}