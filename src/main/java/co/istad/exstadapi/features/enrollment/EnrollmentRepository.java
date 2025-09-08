package co.istad.exstadapi.features.enrollment;

import co.istad.exstadapi.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByIsAcceptedAndIsAchievedAndIsPassed(Boolean isAccepted, Boolean isAchieved, Boolean isPassed);

    List<Enrollment> findAllByIsAchieved(Boolean isAchieved);

    Optional<Enrollment> findByUuid(String uuid);
}
