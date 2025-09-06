package istad.co.exstadbackendapi.mapper;

import istad.co.exstadbackendapi.audit.AuditableMapper;
import istad.co.exstadbackendapi.domain.Achievement;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequest;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementRequestUpdate;
import istad.co.exstadbackendapi.features.achievement.dto.AchievementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {AuditableMapper.class, MapperHelper.class})
public interface AchievementMapper
{
    @Mapping(source = "openingProgramUuid", target = "openingProgram", qualifiedByName = "toOpeningProgramByUuid")
    Achievement toAchievement(AchievementRequest achievementRequest);

    @Mapping(source = ".", target = "audit")
    @Mapping(source = "openingProgram.program.title", target = "program")
    AchievementResponse fromAchievement(Achievement achievement);


    void toAchievementPartially(AchievementRequestUpdate achievementRequestUpdate, @MappingTarget Achievement achievement);

}
