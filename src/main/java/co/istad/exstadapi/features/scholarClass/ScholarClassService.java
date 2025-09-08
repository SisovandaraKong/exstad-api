package co.istad.exstadapi.features.scholarClass;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.features.scholarClass.dto.ScholarClassRequest;
import co.istad.exstadapi.features.scholarClass.dto.ScholarClassResponse;
import co.istad.exstadapi.features.scholarClass.dto.ScholarClassUpdate;

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
