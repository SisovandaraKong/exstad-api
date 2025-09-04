package istad.co.exstadbackendapi.features.certificate;


import istad.co.exstadbackendapi.features.certificate.dto.CertificateRequestDto;
import istad.co.exstadbackendapi.features.document.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateServiceImpl {
    private final DocumentService documentService;

    public byte[] generateCertificate(CertificateRequestDto request) throws Exception {
        if (request.studentName() == null || request.studentName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name is required.");
        }
        if (request.bgImage() == null || request.bgImage().trim().isEmpty()) {
            throw new IllegalArgumentException("Template filename is required.");
        }

        Resource resource = null;
        try {
            ResponseEntity<Resource> response = documentService.downloadDocument(request.bgImage());
            log.info("Certificate template file: {}", response.getHeaders().getLocation());
            resource = response.getBody();
        } catch (Exception e) {
            log.error("Failed to download background image '{}': {}", request.bgImage(), e.getMessage());
            throw new IllegalArgumentException("Background image '" + request.bgImage() + "' not found. Please ensure the image is uploaded to the document system first.");
        }

        if (resource == null || !resource.exists()) {
            throw new IllegalArgumentException("Template file not found or empty.");
        }


        String reportPath = "/reports/Certificate.jrxml";
        log.info("Looking for path: {}", reportPath);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);


        BufferedImage backgroundImage;
        try (InputStream pdfInputStream = resource.getInputStream()) {
            try (PDDocument document = PDDocument.load(pdfInputStream)) {
                PDFRenderer renderer = new PDFRenderer(document);
                backgroundImage = renderer.renderImageWithDPI(0, 150);
            }
        }


        // Set parameters for Jasper
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("studentName", request.studentName());
        parameters.put("bgImage", backgroundImage); // java.awt.Image

        // Fill and export report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public BufferedImage loadBackgroundPdfAsImage() throws IOException {
        // Load PDF from classpath
        try (InputStream pdfStream = getClass().getClassLoader().getResourceAsStream("reports/bg.pdf")) {
            if (pdfStream == null) {
                throw new IOException("PDF file not found: generate/bg.pdf");
            }

            // Copy stream to memory (PDFBox needs mark/reset support)
            byte[] pdfBytes = pdfStream.readAllBytes();

            try (PDDocument document = PDDocument.load(pdfBytes)) {
                PDFRenderer renderer = new PDFRenderer(document);
                // Render first page at 150 DPI (good for screen & print)
                return renderer.renderImageWithDPI(0, 150); // page 0, 150 DPI
            }
        }
    }
}
