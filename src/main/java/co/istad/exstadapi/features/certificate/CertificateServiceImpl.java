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
    private final OpeningProgramRepository openingProgramRepository;


    @Override
    public CertificateResponse generateCertificate(String offeringType,CertificateRequestDto request) {
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
            OpeningProgram openingProgram = openingProgramRepository.findByUuid(request.openingProgramUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));
            for (String scholarUuid : request.scholarUuids()) {
                if (scholarUuid == null || scholarUuid.trim().isEmpty()) {
                    continue;
                }

                Optional<Scholar> scholar = scholarRepository.findByUuid(scholarUuid);
                certificate.setScholar(scholar.orElse(null));
                certificate.setOpeningProgram(openingProgram);
                // For testing purpose
//                certificate.setOpeningProgram(null);
                certificate.setCertificateUrl(null);
                certificate.setIsDisabled(false);
                certificate.setIsDeleted(false);
                certificate.setIsVerified(false);
                certificate.setVerifiedAt(null);
                certificateRepository.save(certificate);



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
                    offeringType,  // I will use request.openingProgram() to fine offering type later
//                    1,
                    openingProgram.getGeneration(),
                    "certificate",
                    "null",
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
    public CertificateResponse verifyCertificate(String offeringType, MultipartFile file, String openingProgramUuid, String scholarUuid) {

        OpeningProgram openingProgram = openingProgramRepository.findByUuid(openingProgramUuid)
                .orElseThrow(() -> new IllegalArgumentException("Opening Program not found"));

        DocumentResponse documentResponse = documentService.uploadDocument(offeringType, openingProgram.getGeneration(), "certificate", "null", file);
        Scholar scholar = scholarRepository.
                findByUuid(scholarUuid).orElseThrow(() -> new IllegalArgumentException("Scholar not found"));
        // This will be used later when opening program is ready
        Optional<Certificate> certificate = certificateRepository.findByScholarAndOpeningProgram(scholar, openingProgram);
//        Optional<Certificate> certificate = certificateRepository.findByScholar(scholar);
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
    public BasedMessage getCertificateByScholar(String scholarUuid) {
        return null;
    }
}