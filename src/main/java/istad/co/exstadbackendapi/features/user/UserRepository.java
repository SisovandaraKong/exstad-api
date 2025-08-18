package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
