package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Province;
import istad.co.exstadbackendapi.features.province.dto.ProvinceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class})
public interface ProvinceMapper {
    @Mapping(target = "audit", source = "province")
    ProvinceResponse toProvinceResponse(Province province);
}
