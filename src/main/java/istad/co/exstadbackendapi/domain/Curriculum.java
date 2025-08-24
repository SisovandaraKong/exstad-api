package istad.co.exstadbackendapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curriculum {
    private Integer id;

    private String title;

    private String description;

    private Boolean isActive;
}
