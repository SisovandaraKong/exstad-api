package istad.co.exstadbackendapi.features.scholarAchievement;

import istad.co.exstadbackendapi.domain.Achievement;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.domain.ScholarAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScholarAchievementRepository extends JpaRepository<ScholarAchievement, Integer> {
    List<ScholarAchievement> findAllByScholar(Scholar scholar);

    Optional<ScholarAchievement> findByScholarAndAchievement(Scholar scholar, Achievement achievement);

    List<ScholarAchievement> findAllByScholarAndIsDeletedFalse(Scholar scholar);
    List<ScholarAchievement> findAllByAchievementAndIsDeletedFalse(Achievement achievement);
}
