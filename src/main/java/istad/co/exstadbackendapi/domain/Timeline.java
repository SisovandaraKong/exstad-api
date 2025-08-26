package istad.co.exstadbackendapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {

    private Integer id;

    private String title;

    private String description;

    private Boolean isActive;
}
