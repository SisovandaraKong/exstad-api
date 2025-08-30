package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.CurrentAddress;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, AuditableMapper.class})
public interface CurrentAddressMapper
{

    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    CurrentAddress toCurrentAddress(CurrentAddressRequest currentAddressRequest);

    @Mapping(source = "province.englishName", target = "province")
    @Mapping(target = "scholars", expression = "java(currentAddress.getScholars != null ? (long) currentAddress.getScholar.size() : 0L")
    @Mapping(source = "currentAddress", target = "audit")
    CurrentAddressResponse fromCurrentAddress(CurrentAddress currentAddress);
}
