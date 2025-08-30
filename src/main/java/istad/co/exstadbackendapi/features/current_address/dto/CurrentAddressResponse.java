package istad.co.exstadbackendapi.features.current_address.dto;

public record CurrentAddressResponse(
        String uuid,
        String englishName,
        String khmerName,
        String province
) {
}
