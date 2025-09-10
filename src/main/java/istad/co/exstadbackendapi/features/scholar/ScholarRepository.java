package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScholarRepository extends JpaRepository<Scholar, Integer> {
    Optional<Scholar> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    void deleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Scholar s SET s.isDeleted = true WHERE s.uuid = ?1")
    void softDeleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Scholar s SET s.isDeleted = false WHERE s.uuid = ?1")
    void restoreByUuid(String uuid);
}

