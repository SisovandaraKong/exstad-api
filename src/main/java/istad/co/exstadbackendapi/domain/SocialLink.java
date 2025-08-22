package istad.co.exstadbackendapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink {
    private String link;
    private String type;
    private String title;
    private Boolean isActive;
}
