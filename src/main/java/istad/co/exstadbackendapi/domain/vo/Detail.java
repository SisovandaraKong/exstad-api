package istad.co.exstadbackendapi.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Detail {

    private String uuid = UUID.randomUUID().toString();

    private String title;

    private String description;

    private Boolean isActive;
}
