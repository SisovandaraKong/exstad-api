package istad.co.exstadbackendapi.features.scholarBadge;

import istad.co.exstadbackendapi.domain.ScholarBadge;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeRequestUpdate;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeResponse;
import istad.co.exstadbackendapi.mapper.ScholarBadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScholarBadgeServiceImpl implements ScholarBadgeService {

    private final ScholarBadgeRepository scholarBadgeRepository;
    private final ScholarBadgeMapper scholarBadgeMapper;

    @Override
    public ScholarBadgeResponse createScholarBadge(ScholarBadgeRequest scholarBadgeRequest) {
        ScholarBadge scholarBadge = scholarBadgeMapper.toScholarBadge(scholarBadgeRequest);
        scholarBadge.setUuid(UUID.randomUUID().toString());
        scholarBadge.setDeleted(false);
        return scholarBadgeMapper.fromScholarBadge(scholarBadgeRepository.save(scholarBadge));
    }

    @Override
    public ScholarBadgeResponse findByUuid(String uuid) {
        return scholarBadgeMapper.fromScholarBadge(
                scholarBadgeRepository.findByUuid(uuid).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar badge not found")
                )
        );
    }

    @Override
    public ScholarBadgeResponse updateScholarBadgeByUuid(String uuid, ScholarBadgeRequestUpdate scholarBadgeRequestUpdate) {
        ScholarBadge scholarBadge = scholarBadgeRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar badge not found")
        );
        scholarBadgeMapper.toScholarBadgePartially(scholarBadgeRequestUpdate, scholarBadge);
        return scholarBadgeMapper.fromScholarBadge(scholarBadgeRepository.save(scholarBadge));
    }

    @Override
    public void deleteScholarBadge(String uuid) {
        ScholarBadge scholarBadge = scholarBadgeRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Scholar badge not found")
        );
        scholarBadge.setDeleted(true);
        scholarBadgeRepository.save(scholarBadge);
    }
}
