package istad.co.exstadbackendapi.mapper;


import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Certificate;
import istad.co.exstadbackendapi.features.certificate.dto.CertificateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class})
public interface CertificateMapper {

    @Mapping(source = "certificate", target = "audit")
    CertificateResponse toCertificateResponse(Certificate certificate);
}
