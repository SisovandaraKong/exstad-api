package istad.co.exstadbackendapi.features.current_address.dto;

public record CurrentAddressRequest(
        String englishName,
        String khmerName,
        String province
) {
}
