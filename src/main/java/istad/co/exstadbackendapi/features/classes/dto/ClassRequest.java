package istad.co.exstadbackendapi.features.classes.dto;

import istad.co.exstadbackendapi.enums.Shift;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;

public record ClassRequest(
        @NotBlank(message = "Opening program UUID is required")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                message = "Opening program UUID must be a valid UUID format")
        String openingProgramUuid,
        @NotBlank(message = "Class name is required")
        @Size(min = 2, max = 100, message = "Class name must be between 2 and 100 characters")
        String className,
        @NotNull(message = "Shift is required")
        Shift shift,
        @NotNull(message = "Weekend status is required")
        Boolean isWeekend,
        @NotNull(message = "Total slot is required")
        @Positive(message = "Total slot must be positive")
        Integer totalSlot,
        @NotNull(message = "Start time is required")
        LocalTime startTime,
        @NotNull(message = "End time is required")
        LocalTime endTime
) {
        public ClassRequest {
                if (startTime != null && endTime != null && !endTime.isAfter(startTime)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"End time must be after start time");
                }
        }
}
