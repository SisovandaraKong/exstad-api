package istad.co.exstadbackendapi.features.enrollment;

import istad.co.exstadbackendapi.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByIsAcceptedAndIsAchievedAndIsPassed(Boolean isAccepted, Boolean isAchieved, Boolean isPassed);

    List<Enrollment> findAllByIsAchieved(Boolean isAchieved);

    Optional<Enrollment> findByUuid(String uuid);
}
