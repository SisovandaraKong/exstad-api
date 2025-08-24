package istad.co.exstadbackendapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Roadmap {

    private Integer id;

    private String title;

    private String description;

    private String imageUrl;

    private Boolean isActive;
}
