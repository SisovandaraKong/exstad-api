package co.istad.exstadapi.features.classes.dto;

import co.istad.exstadapi.enums.Shift;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record ClassUpdate(
        @Size(min = 2, max = 100, message = "Class name must be between 2 and 100 characters")
        String className,
        Shift shift,
        Boolean isWeekend,
        @Positive(message = "Total slot must be positive")
        Integer totalSlot,
        LocalTime startTime,
        LocalTime endTime
) {
    public ClassUpdate {
        if (startTime != null && endTime != null && !endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}
