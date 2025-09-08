package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.features.certificate.dto.CertificateResponse;
import co.istad.exstadapi.features.certificate.dto.CertificateRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface CertificateService {

    CertificateResponse generateCertificate(String offeringType, CertificateRequestDto request);
//    BasedMessage saveCertificateRecord(CertificateRequest certificateRequest);
    CertificateResponse verifyCertificate(String offeringType, MultipartFile file,String openingProgramUuid, String scholarUuid);
    BasedMessage getCertificateByScholar(String scholarUuid);
}
