package istad.co.exstadbackendapi.features.scholarAchievement;

import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementRequest;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementResponseForAchievement;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementResponseForScholar;

import java.util.List;

public interface ScholarAchievementService {

    ScholarAchievementResponseForScholar createScholarAchievement(String scholarUuid, ScholarAchievementRequest achievementRequest);

    List<ScholarAchievementResponseForScholar> getAllScholarAchievementsByUuid(String scholarUuid);

    void removeScholarAchievement(String scholarUuid, String achievementUuid);

    List<ScholarAchievementResponseForAchievement> getAllScholarAchievementsByAchievementUuid(String achievementUuid);
}
