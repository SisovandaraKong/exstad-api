package co.istad.exstadapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@AllArgsConstructor
public enum OfferingType {
    SHORT_COURSE("shortcourse","/short-courses"),
    FULL_STACK_WEB_DEVELOPMENT("fswd","/full-stack-web-development"),
    IT_PROFESSIONAL("itp","/it-professional"),
    IT_EXPERT("ite","it-expert"),
    PRE_UNIVERSITY("pre-uni","/pre-university"),
    FOUNDATION("found","/foundation");

    private final String key;
    private final String folderPath;

    public static OfferingType fromKey(String key) {
        for (OfferingType type : OfferingType.values()) {
            if (type.getKey().toLowerCase().equals(key)) {
                return type;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Offering Type");
    }
}
