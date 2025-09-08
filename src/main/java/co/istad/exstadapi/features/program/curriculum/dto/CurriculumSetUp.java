package co.istad.exstadapi.features.program.curriculum.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CurriculumSetUp(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
        String title,
        @NotEmpty(message = "Curriculum types are required")
        @Valid
        List<CurriculumTypeSetUp> curriculumType
) {
}
