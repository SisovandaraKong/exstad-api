package istad.co.exstadbackendapi.features.achievement;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequest;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequestUpdate;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementResponse;

import java.util.List;

public interface AchievementService {

    AchievementResponse createAchievement(AchievementRequest achievementRequest);

    AchievementResponse getAchievementByTitle(String title);
    AchievementResponse getAchievementByUuid(String uuid);
    List<AchievementResponse> getAllAchievements();
    List<AchievementResponse> getAchievementByOpeningProgramUuid(String openingProgramUuid);

    AchievementResponse updateAchievementByUuid(String uuid, AchievementRequestUpdate achievementRequestUpdate);
    BasedMessage deleteAchievementByUuid(String uuid);



}
