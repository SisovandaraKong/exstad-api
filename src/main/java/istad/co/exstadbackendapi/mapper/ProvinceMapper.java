package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.Province;
import istad.co.exstadbackendapi.features.province.dto.ProvinceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
    ProvinceResponse toProvinceResponse(Province province);
}
