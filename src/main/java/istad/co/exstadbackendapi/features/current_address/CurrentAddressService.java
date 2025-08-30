package istad.co.exstadbackendapi.features.current_address;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressResponse;

import java.util.List;

public interface CurrentAddressService {
    List<CurrentAddressResponse> getAllCurrentAddresses();
    CurrentAddressResponse getCurrentAddressByUuid(String uuid);
    CurrentAddressResponse createCurrentAddress(CurrentAddressRequest currentAddressRequest);
    BasedMessage deleteCurrentAddressByUuid(String uuid);
    
}
