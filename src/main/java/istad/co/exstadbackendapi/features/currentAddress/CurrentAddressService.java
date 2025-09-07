package istad.co.exstadbackendapi.features.currentAddress;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.currentAddress.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.currentAddress.dto.CurrentAddressResponse;

import java.util.List;

public interface CurrentAddressService {
    List<CurrentAddressResponse> getAllCurrentAddresses();
    CurrentAddressResponse getCurrentAddressByUuid(String uuid);
    CurrentAddressResponse createCurrentAddress(CurrentAddressRequest currentAddressRequest);
    BasedMessage deleteCurrentAddressByUuid(String uuid);
    BasedMessage hardDeleteCurrentAddressByUuid(String uuid);
}
