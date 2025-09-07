package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.ScholarAchievement;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementRequest;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.ScholarAchievementResponseForScholar;
import istad.co.exstadbackendapi.features.scholarAchievement.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuditableMapper.class, MapperHelper.class, ScholarMapper.class, AchievementMapper.class})
public interface ScholarAchievementMapper {

    @Mapping(source = "achievementUuid", target = "achievement", qualifiedByName = "toAchievementByUuid")
    ScholarAchievement toScholarAchievement(ScholarAchievementRequest scholarAchievementRequest);

    @Mapping(source = ".", target = "audit")
    @Mapping(source = "achievement", target = "achievement")
    ScholarAchievementResponseForScholar fromScholarAchievement(ScholarAchievement scholarAchievement);

    @Mapping(source = ".", target = "audit")
    @Mapping(source = "scholar", target = "scholar")
    ScholarAchievementResponseForAchievement fromScholarAchievementToAchievement(ScholarAchievement scholarAchievement);
}
