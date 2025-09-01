package istad.co.exstadbackendapi.features.badge;

import istad.co.exstadbackendapi.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {

    Optional<Badge> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
    @Modifying
    @Query("UPDATE Badge b SET b.isDeleted = true WHERE b.uuid = ?1")
    void softDeleteByUuid(String uuid);

    @Modifying
    @Query("UPDATE Badge b SET b.isDeleted = false WHERE b.uuid = ?1")
    void restoreByUuid(String uuid);

    void deleteByUuid(String uuid);
}

