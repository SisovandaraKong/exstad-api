package co.istad.exstadapi.domain.vo;

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
public class Activity {
    private String uuid = UUID.randomUUID().toString();
    private String title;
    private List<ActivityType> activityType;
}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class ActivityType{
    private String uuid = UUID.randomUUID().toString();
    private String title;
    private String description;
    private String image;
}
