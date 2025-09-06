package istad.co.exstadbackendapi.features.classes.dto;

import istad.co.exstadbackendapi.enums.Shift;

import java.time.LocalTime;

public record ClassUpdate(
        String className,
        Shift shift,
        Boolean isWeekend,
        Integer totalSlot,
        LocalTime startTime,
        LocalTime endTime
) {
}
