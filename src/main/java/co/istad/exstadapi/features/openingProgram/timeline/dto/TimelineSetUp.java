package co.istad.exstadapi.features.openingProgram.timeline.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TimelineSetUp(
        @NotNull(message = "Date is required")
        @FutureOrPresent(message = "Date must be today or in the future")
        LocalDate date,

        @NotBlank(message = "Title is required")
        @Size(max = 150, message = "Title must not exceed 150 characters")
        String title
) {
}
