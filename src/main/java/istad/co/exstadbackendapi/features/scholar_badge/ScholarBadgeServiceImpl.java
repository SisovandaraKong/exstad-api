package istad.co.exstadbackendapi.features.scholar_badge;

import istad.co.exstadbackendapi.domain.ScholarBadge;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;
import istad.co.exstadbackendapi.mapper.ScholarBadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return scholarBadgeMapper.fromScholarBadge(scholarBadgeRepository.save(scholarBadge));
    }
}
