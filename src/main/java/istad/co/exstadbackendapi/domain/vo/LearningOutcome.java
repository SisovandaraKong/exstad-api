package istad.co.exstadbackendapi.domain.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LearningOutcome {

    private String uuid = UUID.randomUUID().toString();

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 150, message = "Title must be between 3 and 150 characters")
    private String title;

    @NotBlank(message = "Subtitle is required")
    @Size(min = 3, max = 300, message = "Subtitle must be between 3 and 300 characters")
    private String subtitle;

    @NotEmpty(message = "At least one description is required")
    private List<@NotBlank(message = "Description must not be blank") String> description;
}
