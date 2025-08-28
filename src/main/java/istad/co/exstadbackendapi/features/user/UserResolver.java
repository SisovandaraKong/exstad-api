package istad.co.exstadbackendapi.features.user;

import istad.co.exstadbackendapi.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserResolver {
    private final UserRepository userRepository;

    public UserResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(String uuid) {
        if (uuid == null) return null;
        return userRepository.findByUuid(uuid)
                .map(User::getUsername)
                .orElse("SYSTEM");
    }
}
