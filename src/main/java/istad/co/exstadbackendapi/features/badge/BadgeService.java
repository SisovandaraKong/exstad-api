package istad.co.exstadbackendapi.features.badge;

import istad.co.exstadbackendapi.base.BasedMessage;
import istad.co.exstadbackendapi.features.badge.dto.BadgeRequest;
import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;
import istad.co.exstadbackendapi.features.badge.dto.BadgeUpdate;

import java.util.List;

public interface BadgeService {
    List<BadgeResponse> getAllBadges();
    BadgeResponse getBadgeByUuid(String uuid);
    BadgeResponse createBadge(BadgeRequest badgeRequest);
    BadgeResponse updateBadge(String uuid, BadgeUpdate badgeUpdate);
    BasedMessage deleteBadge(String uuid);
    BasedMessage restoreBadge(String uuid);
    BasedMessage hardDeleteBadge(String uuid);
}
