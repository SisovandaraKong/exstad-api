package istad.co.exstadbackendapi.features.university;

import istad.co.exstadbackendapi.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findByUuid(String uuid);
    boolean existsByEnglishName(String englishName);
    boolean existsByKhmerName(String khmerName);
    boolean existsByUuid(String uuid);
}
