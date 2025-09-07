package istad.co.exstadbackendapi.features.scholarClass.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScholarClassRequest(
        @NotBlank(message = "Scholar UUID is required")
        String scholarUuid,

        @NotBlank(message = "Class UUID is required")
        String classUuid,

        @NotNull(message = "Payment status is required")
        Boolean isPaid,

        @NotNull(message = "Reminder status is required")
        Boolean isReminded
) {
}
