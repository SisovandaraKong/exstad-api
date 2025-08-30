package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.domain.User;
import istad.co.exstadbackendapi.enums.Role;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import istad.co.exstadbackendapi.features.user.UserRepository;
import istad.co.exstadbackendapi.features.user.UserService;
import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import istad.co.exstadbackendapi.mapper.ScholarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScholarServiceImpl implements ScholarService {

    private final ScholarRepository scholarRepository;
    private final ScholarMapper scholarMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public ScholarResponse createScholar(ScholarRequest scholarRequest) {

        UserRequest userRequest = new UserRequest(
                scholarRequest.username(),
                scholarRequest.email(),
                scholarRequest.password(),
                scholarRequest.cfPassword(),
                scholarRequest.englishName(),
                scholarRequest.khmerName(),
                scholarRequest.gender(),
                scholarRequest.dob(),
                Role.SCHOLAR
        );

        UserResponse userResponse = userService.createUser(userRequest);
        User user = userRepository.findByUuid(userResponse.uuid()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        Scholar scholar = scholarMapper.toScholar(scholarRequest);
        scholar.setUser(user);
        scholar.setUuid(user.getUuid());
        scholar.setIsDeleted(false);
        return scholarMapper.fromScholar(scholarRepository.save(scholar));
    }

    @Override
    public ScholarResponse updateScholar(String uuid, ScholarRequestUpdate scholarRequestUpdate) {
        Scholar scholar = scholarRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        scholarMapper.toScholarPartially(scholarRequestUpdate, scholar);
        return scholarMapper.fromScholar(scholarRepository.save(scholar));
    }
}
