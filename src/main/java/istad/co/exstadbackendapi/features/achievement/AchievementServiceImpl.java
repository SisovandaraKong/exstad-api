package istad.co.exstadbackendapi.features.achievement;

import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequest;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequestUpdate;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public AchievementResponse createAchievement(AchievementRequest achievementRequest) {
        return null;
    }

    @Override
    public AchievementResponse getAchievementByTitle(String title) {
        return null;
    }

    @Override
    public AchievementResponse getAchievementByUuid(String uuid) {
        return null;
    }

    @Override
    public List<AchievementResponse> getAllAchievements() {
        return List.of();
    }

    @Override
    public List<AchievementResponse> getAchievementByOpeningProgramUuid(String openingProgramUuid) {
        return List.of();
    }

    @Override
    public AchievementResponse updateAchievementByUuid(String uuid, AchievementRequestUpdate achievementRequestUpdate) {
        return null;
    }

    @Override
    public void deleteAchievementByUuid(String uuid) {

    }
}
