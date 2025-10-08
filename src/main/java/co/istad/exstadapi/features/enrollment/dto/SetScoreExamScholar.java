package co.istad.exstadapi.features.enrollment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

public record SetScoreExamScholar(
        @DecimalMin(value = "0.0", message = "Original fee must be zero or positive")
        @Digits(integer = 8, fraction = 2, message = "Original fee must have at most 8 integer digits and 2 decimal places")
        BigDecimal score
) {
}
