package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.features.certificate.dto.CertificateResponse;
import co.istad.exstadapi.features.certificate.dto.CertificateRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CertificateService {

    CertificateResponse generateCertificate(String offeringType, CertificateRequestDto request);

    CertificateResponse verifyCertificate(String offeringType, MultipartFile file,String openingProgramUuid, String scholarUuid);

    List<CertificateResponse> getAllCertificates();

    CertificateResponse getCertificateByScholarAndOpeningProgram(String scholarUuid, String openingProgramUuid);

    BasedMessage deleteCertificateByScholarAndOpeningProgram(String scholarUuid, String openingProgramUuid);
}
