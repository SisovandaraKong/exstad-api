package co.istad.exstadapi.features.scholar.dto;

import co.istad.exstadapi.audit.AuditableDto;
import co.istad.exstadapi.enums.Gender;
import co.istad.exstadapi.enums.Role;
import co.istad.exstadapi.features.scholarBadge.dto.ScholarBadgeForScholarResponse;

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

        String quote,

        AuditableDto audit,

        Boolean isPublic,

        List<ScholarBadgeForScholarResponse> badges
) {
}
