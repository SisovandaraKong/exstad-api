package istad.co.exstadbackendapi.features.current_address;

import istad.co.exstadbackendapi.domain.CurrentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentAddressRepository extends JpaRepository<CurrentAddress, Integer> {
    Optional<CurrentAddress> findByUuid(String uuid);

    Optional<CurrentAddress> findByEnglishName(String englishName);
}
