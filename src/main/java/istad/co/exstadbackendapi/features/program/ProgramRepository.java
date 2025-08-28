package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Optional<Program> findByUuid(String uuid);
}
