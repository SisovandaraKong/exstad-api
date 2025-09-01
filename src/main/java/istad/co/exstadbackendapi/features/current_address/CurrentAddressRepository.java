package istad.co.exstadbackendapi.features.current_address;

import istad.co.exstadbackendapi.domain.CurrentAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CurrentAddressRepository extends JpaRepository<CurrentAddress, Integer> {
    Optional<CurrentAddress> findByUuid(String uuid);

    Optional<CurrentAddress> findByEnglishName(String englishName);

    boolean existsByUuid(String uuid);

    @Modifying
    @Query("UPDATE CurrentAddress c SET c.isDeleted = true WHERE c.uuid = ?1")
    void softDeleteByUuid(String uuid);
}
