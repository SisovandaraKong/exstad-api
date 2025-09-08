package co.istad.exstadapi.mapper;


import co.istad.exstadapi.audit.AuditableMapper;
import co.istad.exstadapi.domain.Certificate;
import co.istad.exstadapi.features.certificate.dto.CertificateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class})
public interface CertificateMapper {

    @Mapping(source = "certificate", target = "audit")
    CertificateResponse toCertificateResponse(Certificate certificate);
}
