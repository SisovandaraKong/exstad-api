package co.istad.exstadapi.features.scholar;

import co.istad.exstadapi.base.BasedMessage;
import co.istad.exstadapi.domain.OpeningProgram;
import co.istad.exstadapi.domain.Scholar;
import co.istad.exstadapi.domain.User;
import co.istad.exstadapi.domain.vo.SocialLink;
import co.istad.exstadapi.enums.Role;
import co.istad.exstadapi.enums.ScholarStatus;
import co.istad.exstadapi.features.openingProgram.OpeningProgramRepository;
import co.istad.exstadapi.features.scholar.dto.*;
import co.istad.exstadapi.features.user.UserRepository;
import co.istad.exstadapi.features.user.UserService;
import co.istad.exstadapi.features.user.dto.UserRequest;
import co.istad.exstadapi.features.user.dto.UserResponse;
import co.istad.exstadapi.mapper.ScholarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScholarServiceImpl implements ScholarService {

    private final ScholarRepository scholarRepository;
    private final ScholarMapper scholarMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OpeningProgramRepository openingProgramRepository;

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
        scholar.setStatus(ScholarStatus.ACTIVE);
        scholar.setIsAbroad(false);
        scholar.setSocialLink(List.of());
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
        return scholarRepository.findAllByIsDeletedFalse().stream().map(
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

    @Override
    public List<ScholarResponse> getAllScholarsByOpeningProgramUuid(String openingProgramUuid) {
        openingProgramRepository.findByUuid(openingProgramUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opening Program not found")
        );

        List<Scholar> scholars = scholarRepository.findAllByOpeningProgramUuid(openingProgramUuid);

        return scholars.stream()
                .map(scholarMapper::fromScholar)
                .toList();
    }

    @Override
    public ScholarResponse getCurrentScholar() {
        String username = userService.getUsernameFromAccessToken();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        if(!user.getRole().equals(Role.SCHOLAR)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this role");
        }
        return scholarMapper.fromScholar(user.getScholar());
    }

    @Override
    public ScholarResponse updateCurrentScholar(ScholarRequestUpdate scholarRequestUpdate) {
        String username = userService.getUsernameFromAccessToken();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        if(!user.getRole().equals(Role.SCHOLAR)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this role");
        }
        return updateScholar(user.getUuid(), scholarRequestUpdate);
    }


    @Override
    public SocialLinkResponse setUpScholarSocialLink(String uuid, SocialLinkRequest socialLinkRequest) {
        Scholar scholar = scholarRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        SocialLink socialLink = new SocialLink();
        socialLink.setUuid(UUID.randomUUID().toString());
        socialLink.setType(socialLinkRequest.type());
        socialLink.setLink(socialLinkRequest.link());
        socialLink.setTitle(socialLinkRequest.title());
        socialLink.setIsActive(true);
        socialLink.setIsDeleted(false);
        scholar.getSocialLink().add(socialLink);
        scholarRepository.save(scholar);
        return SocialLinkResponse.builder()
                .uuid(socialLink.getUuid())
                .title(socialLink.getTitle())
                .type(socialLink.getType())
                .link(socialLink.getLink())
                .isActive(socialLink.getIsActive())
                .build();
    }

    @Override
    public List<SocialLinkResponse> getScholarSocialLink(String uuid) {
        return scholarRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        ).getSocialLink().stream().map(
                socialLink -> SocialLinkResponse.builder()
                        .uuid(socialLink.getUuid())
                        .title(socialLink.getTitle())
                        .type(socialLink.getType())
                        .link(socialLink.getLink())
                        .isActive(socialLink.getIsActive())
                        .build()
        ).toList();
    }

    @Override
    public SocialLinkResponse updateSocialLinkStatus(String scholarUuid, String socialLinkUuid, boolean status) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        SocialLink socialLink = scholar.getSocialLink().stream().filter(
                s -> s.getUuid().equals(socialLinkUuid)
        ).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SocialLink not found")
        );
        socialLink.setIsActive(status);
        scholarRepository.save(scholar);
        return SocialLinkResponse.builder()
                .uuid(socialLink.getUuid())
                .title(socialLink.getTitle())
                .type(socialLink.getType())
                .link(socialLink.getLink())
                .isActive(socialLink.getIsActive())
                .build();
    }

    @Override
    public void deleteSocialLink(String scholarUuid, String socialLinkUuid) {
        Scholar scholar = scholarRepository.findByUuid(scholarUuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar not found")
        );
        SocialLink socialLink = scholar.getSocialLink().stream().filter(
                s -> s.getUuid().equals(socialLinkUuid)
        ).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SocialLink not found")
        );
        socialLink.setIsActive(true);
        scholarRepository.save(scholar);
    }

    @Override
    public List<ScholarResponse> getAllScholarsByStatus(ScholarStatus scholarStatus) {
        return scholarRepository.findAllByStatusAndIsDeletedFalse(scholarStatus).stream().map(scholarMapper::fromScholar).toList() ;
    }

    @Override
    public List<ScholarResponse> getAllAbroadScholars() {
        return scholarRepository.findAllByIsAbroadTrueAndIsDeletedFalse().stream().map(scholarMapper::fromScholar).toList() ;
    }
}
