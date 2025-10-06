package co.istad.exstadapi.features.program;

import co.istad.exstadapi.domain.OpeningProgram;
import co.istad.exstadapi.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Optional<Program> findByUuid(String uuid);
    Optional<Program> findBySlug(String slug);
    Optional<Program> findByTitleIgnoreCase(String title);
    boolean existsByUuid(String uuid);
    List<Program> findAllByIsDeletedFalse();

    @Modifying
    @Query("UPDATE Program p SET p.isDeleted = true WHERE p.uuid = ?1")
    void softDeleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Program p SET p.isDeleted = false WHERE p.uuid = ?1")
    void undeleteByUuid(String uuid);

    void deleteByUuid(String uuid);

    Optional<Program> findBySlugAndIsDeletedFalse(String slug);
    Optional<Program> findByOpeningPrograms(OpeningProgram openingProgram);
}
