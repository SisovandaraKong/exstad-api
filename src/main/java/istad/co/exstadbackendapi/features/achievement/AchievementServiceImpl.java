package istad.co.exstadbackendapi.features.achievement;

import istad.co.exstadbackendapi.domain.Achievement;
import istad.co.exstadbackendapi.domain.OpeningProgram;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequest;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequestUpdate;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementResponse;
import istad.co.exstadbackendapi.features.openingProgram.OpeningProgramRepository;
import istad.co.exstadbackendapi.mapper.AchievementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;
    private final OpeningProgramRepository openingProgramRepository;

    @Override
    public AchievementResponse createAchievement(AchievementRequest achievementRequest) {

        Achievement achievement = achievementMapper.toAchievement(achievementRequest);
        achievement.setUuid(UUID.randomUUID().toString());
        achievement.setIsDeleted(false);
        achievement = achievementRepository.save(achievement);

        return achievementMapper.fromAchievement(achievement);
    }

    @Override
    public AchievementResponse getAchievementByTitle(String title) {
        return achievementMapper.fromAchievement(
                achievementRepository.findByTitle(title).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No achievement found with title " + title)
                )
        );
    }

    @Override
    public AchievementResponse getAchievementByUuid(String uuid) {
        return achievementMapper.fromAchievement(
                achievementRepository.findByUuid(uuid).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No achievement found with uuid " + uuid)
                )
        );
    }

    @Override
    public List<AchievementResponse> getAllAchievements() {

        return achievementRepository.findAll().stream().map(
                achievementMapper::fromAchievement
        ).toList();
    }

    @Override
    public List<AchievementResponse> getAchievementByOpeningProgramUuid(String openingProgramUuid) {
        OpeningProgram openingProgram = openingProgramRepository.findByUuid(openingProgramUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No opening program found with uuid " + openingProgramUuid)
        );

        return achievementRepository.findAllByOpeningProgram(openingProgram).stream().map(
                achievementMapper::fromAchievement
        ).toList();
    }

    @Override
    public AchievementResponse updateAchievementByUuid(String uuid, AchievementRequestUpdate achievementRequestUpdate) {
        Achievement achievement = achievementRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No achievement found with uuid " + uuid)
        );
        achievementMapper.toAchievementPartially(achievementRequestUpdate, achievement);
        achievement = achievementRepository.save(achievement);
        return achievementMapper.fromAchievement(achievement);
    }

    @Override
    public void deleteAchievementByUuid(String uuid) {
        Achievement achievement = achievementRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No achievement found with uuid " + uuid)
        );
        achievement.setIsDeleted(true);
        achievementRepository.save(achievement);
    }
}
