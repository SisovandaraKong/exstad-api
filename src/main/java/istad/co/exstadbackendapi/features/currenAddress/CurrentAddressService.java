package istad.co.exstadbackendapi.features.currenAddress;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.currenAddress.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.currenAddress.dto.CurrentAddressResponse;

import java.util.List;

public interface CurrentAddressService {
    List<CurrentAddressResponse> getAllCurrentAddresses();
    CurrentAddressResponse getCurrentAddressByUuid(String uuid);
    CurrentAddressResponse createCurrentAddress(CurrentAddressRequest currentAddressRequest);
    BasedMessage deleteCurrentAddressByUuid(String uuid);
    BasedMessage hardDeleteCurrentAddressByUuid(String uuid);
}
