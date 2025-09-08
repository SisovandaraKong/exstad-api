package co.istad.exstadapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink {

    private int id;

    private String link;

    private String type;

    private String title;

    private Boolean isActive;
}
