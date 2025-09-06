package istad.co.exstadbackendapi.features.scholarAchievement;

import istad.co.exstadbackendapi.domain.Achievement;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.domain.ScholarAchievement;
import istad.co.exstadbackendapi.features.achievement.AchievementRepository;
import istad.co.exstadbackendapi.features.scholar.ScholarRepository;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementRequest;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementResponseForAchievement;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementResponseForScholar;
import istad.co.exstadbackendapi.mapper.ScholarAchievementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScholarAchievementServiceImpl implements ScholarAchievementService {

    private final ScholarAchievementRepository scholarAchievementRepository;
    private final ScholarRepository scholarRepository;
    private final ScholarAchievementMapper scholarAchievementMapper;
    private final AchievementRepository achievementRepository;

    @Override
    public ScholarAchievementResponseForScholar createScholarAchievement(String scholarUuid, ScholarAchievementRequest achievementRequest) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        ScholarAchievement scholarAchievement = scholarAchievementMapper.toScholarAchievement(achievementRequest);
        scholarAchievement.setScholar(scholar);
        scholarAchievement.setUuid(UUID.randomUUID().toString());
        scholarAchievement.setIsDeleted(false);
        scholarAchievement = scholarAchievementRepository.save(scholarAchievement);
        return scholarAchievementMapper.fromScholarAchievement(scholarAchievement);
    }

    @Override
    public List<ScholarAchievementResponseForScholar> getAllScholarAchievementsByUuid(String scholarUuid) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        return scholarAchievementRepository.findAllByScholarAndIsDeletedFalse(scholar).stream().map(
                scholarAchievementMapper::fromScholarAchievement
        ).toList();
    }

    @Override
    public void removeScholarAchievement(String scholarUuid, String achievementUuid) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        Achievement achievement = achievementRepository.findByUuid(achievementUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found")
        );
        ScholarAchievement scholarAchievement = scholarAchievementRepository.findByScholarAndAchievement(scholar, achievement).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No scholar's achievement found")
        );
        scholarAchievement.setIsDeleted(true);
         scholarAchievementRepository.save(scholarAchievement);

    }

    @Override
    public List<ScholarAchievementResponseForAchievement> getAllScholarAchievementsByAchievementUuid(String achievementUuid) {
        Achievement achievement = achievementRepository.findByUuid(achievementUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement not found")
        );
        return scholarAchievementRepository.findAllByAchievementAndIsDeletedFalse(achievement).stream().map(
                scholarAchievementMapper::fromScholarAchievementToAchievement
        ).toList();
    }
}
