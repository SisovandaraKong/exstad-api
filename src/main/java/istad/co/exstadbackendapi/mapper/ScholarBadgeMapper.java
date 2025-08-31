package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.domain.ScholarBadge;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeRequest;
import istad.co.exstadbackendapi.features.scholar_badge.dto.ScholarBadgeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, BadgeMapper.class})
public interface ScholarBadgeMapper {

    @Mapping(source = "badgeUuid", target = "badge", qualifiedByName = "toBadge")
    @Mapping(source = "scholarUuid", target = "scholar", qualifiedByName = "toScholar")
    ScholarBadge toScholarBadge(ScholarBadgeRequest scholarBadgeRequest);

    @Mapping(source = "badge", target = "badge")
    ScholarBadgeResponse fromScholarBadge(ScholarBadge scholarBadge);

    List<ScholarBadgeResponse> toScholarBadgeResponses(List<ScholarBadge> scholarBadges);
}
