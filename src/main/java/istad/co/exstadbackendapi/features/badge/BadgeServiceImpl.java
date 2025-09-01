package istad.co.exstadbackendapi.features.badge;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.domain.Badge;
import istad.co.exstadbackendapi.features.badge.dto.BadgeRequest;
import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;
import istad.co.exstadbackendapi.features.badge.dto.BadgeUpdate;
import istad.co.exstadbackendapi.mapper.BadgeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    private final BadgeRepository badgeRepository;
    private final BadgeMapper badgeMapper;

    @Override
    public List<BadgeResponse> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(badgeMapper::toBadgeResponse)
                .toList();
    }

    @Override
    public BadgeResponse getBadgeByUuid(String uuid) {
        Badge badge = badgeRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge not found"));
        return badgeMapper.toBadgeResponse(badge);
    }

    @Override
    public BadgeResponse createBadge(BadgeRequest badgeRequest) {
        Badge badge = badgeMapper.fromBadgeRequest(badgeRequest);

        badge.setUuid(UUID.randomUUID().toString());
        badge.setIsDeleted(false);
        badge = badgeRepository.save(badge);
        return badgeMapper.toBadgeResponse(badge);
    }

    @Override
    public BadgeResponse updateBadge(String uuid, BadgeUpdate badgeUpdate) {
        Badge badge = badgeRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge not found"));

        badgeMapper.updateBadgeFromRequest(badgeUpdate, badge);
        badge = badgeRepository.save(badge);
        return badgeMapper.toBadgeResponse(badge);
    }

    @Transactional
    @Override
    public BasedMessage deleteBadge(String uuid) {
        if (!badgeRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Badge not found");
        }
        badgeRepository.softDeleteByUuid(uuid);
        return new BasedMessage("Badge deleted successfully");
    }

    @Transactional
    @Override
    public BasedMessage restoreBadge(String uuid) {
        if (!badgeRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Badge not found");
        }
        badgeRepository.restoreByUuid(uuid);
        return new BasedMessage("Badge restored successfully");
    }

    @Transactional
    @Override
    public BasedMessage hardDeleteBadge(String uuid) {
        if (!badgeRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Badge not found");
        }
        badgeRepository.deleteByUuid(uuid);
        return new BasedMessage("Badge hard deleted successfully");
    }
}
