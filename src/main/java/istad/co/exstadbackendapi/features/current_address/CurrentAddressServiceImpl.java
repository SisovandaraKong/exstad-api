package istad.co.exstadbackendapi.features.current_address;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.CurrentAddress;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressResponse;
import istad.co.exstadbackendapi.mapper.CurrentAddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrentAddressServiceImpl implements CurrentAddressService {

    private final CurrentAddressRepository currentAddressRepository;
    private final CurrentAddressMapper currentAddressMapper;

    @Override
    public List<CurrentAddressResponse> getAllCurrentAddresses() {
        List<CurrentAddress> currentAddresses = currentAddressRepository.findAll();
        return currentAddresses.stream().map(
                currentAddressMapper::fromCurrentAddress
        ).toList();
    }

    @Override
    public CurrentAddressResponse getCurrentAddressByUuid(String uuid) {
        return currentAddressMapper.fromCurrentAddress(
               currentAddressRepository.findByUuid(uuid).orElseThrow(
                        () -> new RuntimeException("Current address not found")
                )
        );
    }

    @Override
    public CurrentAddressResponse createCurrentAddress(CurrentAddressRequest currentAddressRequest) {
        CurrentAddress currentAddress = currentAddressMapper.toCurrentAddress(currentAddressRequest);
        currentAddress.setUuid(UUID.randomUUID().toString());
        return currentAddressMapper.fromCurrentAddress(currentAddressRepository.save(currentAddress));
    }

    @Override
    public BasedMessage deleteCurrentAddressByUuid(String uuid) {
        CurrentAddress currentAddress = currentAddressRepository.findByUuid(uuid).orElseThrow(
                () -> new RuntimeException("Current address not found")
        );
        currentAddress.setDeleted(true);
        currentAddressRepository.save(currentAddress);
        return new BasedMessage("Current address deleted successfully");
    }
}
