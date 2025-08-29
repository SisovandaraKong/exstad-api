package istad.co.exstadbackendapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curriculum {
    private int id;
    private String title;
    private List<CurriculumType> curriculumType;
}

class CurriculumType {
    private int id;
    private int order;
    private String title;
    private String subtitle;
    private List<String> description;
}
