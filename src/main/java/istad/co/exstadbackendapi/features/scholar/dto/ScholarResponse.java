package istad.co.exstadbackendapi.features.scholar.dto;

import istad.co.exstadbackendapi.audit.AuditableDto;
import istad.co.exstadbackendapi.enums.Gender;
import istad.co.exstadbackendapi.enums.Role;
import istad.co.exstadbackendapi.features.scholarBadge.dto.ScholarBadgeForScholarResponse;

import java.time.LocalDate;
import java.util.List;

public record ScholarResponse(
        String uuid,

        String username,

        String email,

        String englishName,

        String khmerName,

        Gender gender,

        LocalDate dob,

        Role role,

        String university,

        String province,

        String currentAddress,

        String nickname,

        String bio,

        String avatar,

        String phoneFamilyNumber,

        Boolean isPublic,

        String quote,

        AuditableDto audit,

        List<ScholarBadgeForScholarResponse> badges
) {
}
