package istad.co.exstadbackendapi.features.classes;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.classes.dto.ClassRequest;
import istad.co.exstadbackendapi.features.classes.dto.ClassResponse;
import istad.co.exstadbackendapi.features.classes.dto.ClassUpdate;

import java.util.List;

public interface ClassService {
    List<ClassResponse> getAllClasses();
    ClassResponse getClassByUuid(String uuid);
    ClassResponse getClassByName(String name);
    ClassResponse createClass(ClassRequest classRequest);
    ClassResponse updateClass(String uuid, ClassUpdate classUpdate);
    BasedMessage softDeleteClass(String uuid);
    BasedMessage restoreClass(String uuid);
    BasedMessage hardDeleteClass(String uuid);
    BasedMessage disableClass(String uuid);
    BasedMessage enableClass(String uuid);
    BasedMessage setToWeekend(String uuid);
    BasedMessage setToWeekday(String uuid);
}
