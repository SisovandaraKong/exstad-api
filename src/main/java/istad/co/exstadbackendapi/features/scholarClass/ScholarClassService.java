package istad.co.exstadbackendapi.features.scholarClass;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassRequest;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassResponse;
import istad.co.exstadbackendapi.features.scholarClass.dto.ScholarClassUpdate;

import java.util.List;

public interface ScholarClassService {

    ScholarClassResponse createScholarIntoClass(ScholarClassRequest scholarClassRequest);
    List<ScholarClassResponse> getAllScholarClasses();
    ScholarClassResponse getScholarClassByUuid(String uuid);
    ScholarClassResponse updateScholarClassByUuid(String uuid, ScholarClassUpdate scholarClassUpdate);
    BasedMessage softDeleteScholarClassByUuid(String uuid);
    BasedMessage restoreScholarClassByUuid(String uuid);
    BasedMessage hardDeleteScholarClassByUuid(String uuid);

    List<ScholarClassResponse> getAllClassesByOneScholarUuid(String scholarUuid);
    List<ScholarClassResponse> getAllScholarsByOneClassUuid(String classUuid);

    BasedMessage markAsPaid(String uuid);
    BasedMessage markAsUnpaid(String uuid);
    BasedMessage markAsReminded(String uuid);
    BasedMessage markAsUnreminded(String uuid);
}
