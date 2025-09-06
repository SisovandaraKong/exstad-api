package istad.co.exstadbackendapi.features.classes.dto;


import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.enums.Shift;

import java.time.LocalTime;

public record ClassResponse(
        String className,
        String uuid,
        String openingProgramName,
        Shift shift,
        Boolean isWeekend,
        Integer totalSlot,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isDeleted,
        Boolean isEnabled,
        AuditableDto audit
) {
}
