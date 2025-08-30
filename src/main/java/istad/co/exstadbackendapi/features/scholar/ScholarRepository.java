package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarRepository extends JpaRepository<Scholar, Integer> {
}
