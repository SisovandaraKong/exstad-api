package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.CurrentAddress;
import istad.co.exstadbackendapi.features.current_address.CurrentAddressMapperHelper;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CurrentAddressMapperHelper.class})
public interface CurrentAddressMapper
{

    @Mapping(source = "province", target = "province", qualifiedByName = "toProvince")
    CurrentAddress toCurrentAddress(CurrentAddressRequest currentAddressRequest);

    @Mapping(source = "province.englishName", target = "province")
    CurrentAddressResponse fromCurrentAddress(CurrentAddress currentAddress);
}
