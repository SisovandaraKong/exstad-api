package istad.co.exstadbackendapi.features.achievement;

import istad.co.exstadbackendapi.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
}
