package istad.co.exstadbackendapi.features.province;

import istad.co.exstadbackendapi.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
