package istad.co.exstadbackendapi.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramOverview {
    private int id;
    private String title;
    private String description;
}
