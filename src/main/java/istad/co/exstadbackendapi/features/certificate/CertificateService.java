package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.features.certificate.dto.CertificateResponse;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface CertificateService {

    CertificateResponse generateCertificate(CertificateRequestDto request);
//    BasedMessage saveCertificateRecord(CertificateRequest certificateRequest);
    CertificateResponse verifyCertificate(String offeringType, int gen, String documentType, String preferredFileName, MultipartFile file,Integer openingProgramId, String uuid);

}
