package istad.co.exstadbackendapi.features.scholar.dto;

import istad.co.exstadbackendapi.audit.Auditable;
import istad.co.exstadbackendapi.enums.Gender;
import istad.co.exstadbackendapi.enums.Role;

import java.time.LocalDate;

public record ScholarResponse(
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

        Auditable audit
) {
}
