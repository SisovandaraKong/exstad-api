package co.istad.exstadapi.features.scholar;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.enums.ScholarStatus;
import co.istad.exstadapi.features.scholar.dto.*;

import java.util.List;

public interface ScholarService {

    ScholarResponse createScholar(ScholarRequest scholarRequest);
    ScholarResponse setMajorToAlumniScholar(SetMajorToAlumniScholar setMajorToAlumniScholar, String uuid);
    ScholarResponse updateScholar(String uuid, ScholarRequestUpdate scholarRequestUpdate);
    List<ScholarResponse> createMultipleScholars(List<ScholarRequest> scholarRequests);

    List<ScholarResponse> findAllScholars();
    ScholarResponse findByUuid(String uuid);
    ScholarResponse findByUsername(String username);
    List<ScholarResponse> searchByEnglishName(String englishName);
    List<ScholarResponse> searchByUsername(String username);

    Long countScholars();

    BasedMessage softDeleteScholarByUuid(String uuid);
    BasedMessage restoreScholarByUuid(String uuid);
    BasedMessage hardDeleteScholarByUuid(String uuid);

    List<ScholarResponse> getAllScholarsByOpeningProgramUuid(String openingProgramUuid);
    List<ScholarResponse> getAllScholarsByStatus(ScholarStatus scholarStatus);
    List<ScholarResponse> getAllAbroadScholars();
    ScholarResponse getCurrentScholar();
    ScholarResponse updateCurrentScholar(ScholarRequestUpdate scholarRequestUpdate);

    SocialLinkResponse setUpScholarSocialLink(String uuid, SocialLinkRequest socialLinkRequest);
    List<SocialLinkResponse> getScholarSocialLink(String uuid);
    SocialLinkResponse updateSocialLinkStatus(String scholarUuid, String socialLinkUuid, boolean status);
    void deleteSocialLink(String scholarUuid, String socialLinkUuid);
}
