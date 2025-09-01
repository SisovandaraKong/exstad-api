package istad.co.exstadbackendapi.features.current_address;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.current_address.dto.CurrentAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/current-addresses")
public class CurrentAddressController {

    private final CurrentAddressService currentAddressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrentAddressResponse> getAllCurrentAddresses() {
        return currentAddressService.getAllCurrentAddresses();
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CurrentAddressResponse getCurrentAddress(@PathVariable String uuid) {
        return currentAddressService.getCurrentAddressByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CurrentAddressResponse createCurrentAddress(@RequestBody CurrentAddressRequest currentAddressRequest) {
        return currentAddressService.createCurrentAddress(currentAddressRequest);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BasedMessage deleteCurrentAddress(@PathVariable String uuid) {
        return currentAddressService.deleteCurrentAddressByUuid(uuid);
    }

}
