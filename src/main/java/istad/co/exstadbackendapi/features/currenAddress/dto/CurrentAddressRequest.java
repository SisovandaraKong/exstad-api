package istad.co.exstadbackendapi.features.currenAddress.dto;

public record CurrentAddressRequest(
        String englishName,
        String khmerName,
        String province
) {
}
