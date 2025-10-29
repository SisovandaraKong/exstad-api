package co.istad.exstadapi.features.applicantLetter;

import co.istad.exstadapi.domain.Enrollment;
import co.istad.exstadapi.enums.DocumentType;
import co.istad.exstadapi.features.applicantLetter.dto.ApplicantLetterRequest;
import co.istad.exstadapi.features.applicantLetter.dto.KhmerDate;
import co.istad.exstadapi.features.certificate.InMemoryMultipartFile;
import co.istad.exstadapi.features.document.DocumentService;
import co.istad.exstadapi.features.document.dto.DocumentResponse;
import co.istad.exstadapi.features.enrollment.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ApplicantLetterServiceImpl implements ApplicantLetterService {

    private final DocumentService documentService;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public byte[] generateApplicantLetter(ApplicantLetterRequest applicantLetterRequest) {

        try{
            KhmerDate khmerDate = KhmerDateConverter.convertToKhmerDate(applicantLetterRequest.issueDate());
            InputStream reportStream = getClass().getResourceAsStream("/generates/ApplicantLetter.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

            // Parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("khmerName", applicantLetterRequest.khmerName());
            parameters.put("englishName", applicantLetterRequest.englishName());
            parameters.put("avatar", applicantLetterRequest.avatar());
            parameters.put("gender", applicantLetterRequest.gender());
            parameters.put("national", applicantLetterRequest.national());
            parameters.put("dob", applicantLetterRequest.dob().toString());
            parameters.put("placeOfBirth", applicantLetterRequest.placeOfBirth());
            parameters.put("university", applicantLetterRequest.university());
            parameters.put("year", applicantLetterRequest.year());
            parameters.put("major", applicantLetterRequest.major());
            parameters.put("educationQualification", applicantLetterRequest.educationQualification());
            parameters.put("currentAddress", applicantLetterRequest.currentAddress());
            parameters.put("day", khmerDate.day());
            parameters.put("month", khmerDate.month());
                parameters.put("years", khmerDate.year());
            parameters.put("number", applicantLetterRequest.number());
            parameters.put("tableNumber", applicantLetterRequest.tableNumber());

            JRDataSource dataSource = new JREmptyDataSource();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
        catch (JRException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] generateApplicantLettersZip(List<ApplicantLetterRequest> requests) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            for (int i = 0; i < requests.size(); i++) {
                ApplicantLetterRequest request = requests.get(i);
                byte[] pdfBytes = generateApplicantLetter(request);

                String fileName = request.englishName() != null
                        ? request.englishName().replaceAll("\\s+", "_") + "_" + "applicant_" + (i + 1) + ".pdf"
                        : "applicant_" + (i + 1) + ".pdf";

                MultipartFile file = new InMemoryMultipartFile(
                        "file",
                        "applicant_letter.pdf",
                        "application/pdf",
                        pdfBytes
                );

                DocumentResponse document = documentService.uploadDocument(
                        request.programSlug() == null ? "null" : request.programSlug(),
                        request.generation() == null ? 0 : request.generation(),
                        "transcript",
                        fileName,
                        file
                );

                Enrollment enrollment = enrollmentRepository.findByUuid(request.enrollmentUuid()).orElse(null);
                if(enrollment != null){
                    enrollment.setApplicantLetter(document.uri());
                    enrollmentRepository.save(enrollment);
                }
                ZipEntry entry = new ZipEntry(fileName);
                zos.putNextEntry(entry);
                zos.write(pdfBytes);
                zos.closeEntry();
            }

            zos.finish();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create ZIP file", e);
        }
    }
}
