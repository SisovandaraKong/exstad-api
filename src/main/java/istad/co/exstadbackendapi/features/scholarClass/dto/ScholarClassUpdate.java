package istad.co.exstadbackendapi.features.scholarClass.dto;

import jakarta.validation.constraints.NotNull;

public record ScholarClassUpdate(
        @NotNull(message = "Payment status is required")
        Boolean isPaid,

        @NotNull(message = "Reminder status is required")
        Boolean isReminded
) {
}
