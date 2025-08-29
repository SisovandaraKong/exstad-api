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
public class Faq {

    private List<FaqSection> faq;

}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class FaqSection {
    private String title;
    private List<FaqItem> faqs;

}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class FaqItem {
    private int id;
    private String question;
    private String answer;

}
