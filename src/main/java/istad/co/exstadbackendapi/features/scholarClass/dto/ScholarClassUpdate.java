package istad.co.exstadbackendapi.features.scholarClass.dto;

import jakarta.validation.constraints.NotNull;

public record ScholarClassUpdate(
        Boolean isPaid,

        Boolean isReminded
) {
}
