package co.istad.exstadapi.features.badge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BadgeRequest(
        @NotBlank(message = "Badge image URL is required")
        String badgeImage,
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
        String title,
        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
        String description
) {
}