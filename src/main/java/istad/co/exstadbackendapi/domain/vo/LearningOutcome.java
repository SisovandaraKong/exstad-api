package istad.co.exstadbackendapi.domain.vo;

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

    private String uuid= UUID.randomUUID().toString();
    private String title;
    private String subtitle;
    private List<String> description;
}
