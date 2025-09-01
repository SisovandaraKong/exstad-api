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
    private String uuid= UUID.randomUUID().toString();
    private String question;
    private String answer;

}
