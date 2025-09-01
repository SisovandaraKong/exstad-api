package istad.co.exstadbackendapi.features.scholar;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.Scholar;
import istad.co.exstadbackendapi.domain.User;
import istad.co.exstadbackendapi.enums.Role;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequest;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarRequestUpdate;
import istad.co.exstadbackendapi.features.scholar.dto.ScholarResponse;
import istad.co.exstadbackendapi.features.user.UserRepository;
import istad.co.exstadbackendapi.features.user.UserService;
import istad.co.exstadbackendapi.features.user.dto.UserRequest;
import istad.co.exstadbackendapi.features.user.dto.UserResponse;
import istad.co.exstadbackendapi.mapper.ScholarBadgeMapper;
import istad.co.exstadbackendapi.mapper.ScholarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ScholarResponse> createMultipleScholars(List<ScholarRequest> scholarRequests) {
        List<ScholarResponse> scholarResponses = new ArrayList<>();
        scholarRequests.forEach(
                scholarRequest -> scholarResponses.add(createScholar(scholarRequest))
        );
        return scholarResponses;
    }

    @Override
    public List<ScholarResponse> findAllScholars() {
        return scholarRepository.findAll().stream().map(
                scholarMapper::fromScholar
        ).toList();
    }

    @Override
    public ScholarResponse findByUuid(String uuid) {
        return scholarMapper.fromScholar(
                scholarRepository.findByUuid(uuid).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
                )
        );
    }

    @Override
    public ScholarResponse findByUsername(String username) {
        return scholarMapper.fromScholar(
                userRepository.findByUsernameAndRole(username, Role.SCHOLAR).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
                ).getScholar()
        );
    }

    @Override
    public List<ScholarResponse> searchByEnglishName(String englishName) {
        return userRepository.findAllByEnglishNameAndRole(englishName, Role.SCHOLAR).stream().map(
                user -> scholarMapper.fromScholar(user.getScholar())
        ).toList();
    }

    @Override
    public List<ScholarResponse> searchByUsername(String username) {
        return userRepository.findAllByUsernameAndRole(username, Role.SCHOLAR).stream().map(
                user -> scholarMapper.fromScholar(user.getScholar())
        ).toList();
    }

    @Override
    public Long countScholars() {
        return scholarRepository.count();
    }

    @Transactional
    @Override
    public BasedMessage softDeleteScholarByUuid(String uuid) {
       if (!scholarRepository.existsByUuid(uuid)) {
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found");
         }
              scholarRepository.softDeleteByUuid(uuid);
              return new BasedMessage("Scholar deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage restoreScholarByUuid(String uuid) {
       if (!scholarRepository.existsByUuid(uuid)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found");
             }
                scholarRepository.restoreByUuid(uuid);
                return new BasedMessage("Scholar restored successfully");
    }

    @Transactional
    @Override
    public BasedMessage hardDeleteScholarByUuid(String uuid) {
        if (!scholarRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found");
        }
            scholarRepository.deleteByUuid(uuid);
            return new BasedMessage("Scholar hard deleted successfully");
    }
}
