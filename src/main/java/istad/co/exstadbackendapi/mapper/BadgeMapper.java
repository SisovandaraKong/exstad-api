package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.Badge;
import istad.co.exstadbackendapi.features.badge.dto.BadgeRequest;
import istad.co.exstadbackendapi.features.badge.dto.BadgeResponse;
import istad.co.exstadbackendapi.features.badge.dto.BadgeUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    @Mapping(target = "audit", source = "badge")
    BadgeResponse toBadgeResponse(Badge badge);

    Badge fromBadgeRequest(BadgeRequest badgeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateBadgeFromRequest(BadgeUpdate badgeUpdate, @MappingTarget Badge badge);

}
