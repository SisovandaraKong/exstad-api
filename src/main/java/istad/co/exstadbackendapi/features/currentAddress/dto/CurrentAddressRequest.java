package istad.co.exstadbackendapi.features.currentAddress.dto;

public record CurrentAddressRequest(
        String englishName,
        String khmerName,
        String province
) {
}
