package istad.co.exstadbackendapi.features.currenAddress;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.currenAddress.dto.CurrentAddressRequest;
import istad.co.exstadbackendapi.features.currenAddress.dto.CurrentAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/current-addresses")
public class CurrentAddressController {

    private final CurrentAddressService currentAddressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllCurrentAddresses() {
        return new ResponseEntity<>(
                Map.of("current addresses",currentAddressService.getAllCurrentAddresses()), HttpStatus.OK);
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

    @PutMapping("{uuid}/soft-delete")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage deleteCurrentAddress(@PathVariable String uuid) {
        return currentAddressService.deleteCurrentAddressByUuid(uuid);
    }


    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BasedMessage hardDeleteCurrentAddress(@PathVariable String uuid) {
        return currentAddressService.hardDeleteCurrentAddressByUuid(uuid);
    }

}
