package istad.co.exstadbackendapi.features.program;

import istad.co.exstadbackendapi.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Optional<Program> findByUuid(String uuid);
    Optional<Program> findByTitleIgnoreCase(String title);
    boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE Program p SET p.isDeleted = true WHERE p.uuid = ?1")
    void softDeleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Program p SET p.isDeleted = false WHERE p.uuid = ?1")
    void undeleteByUuid(String uuid);

    void deleteByUuid(String uuid);
}
