package istad.co.exstadbackendapi.features.scholar.dto;

public record ScholarRequestUpdate (
        String university,

        String province,

        String currentAddress,

        String nickname,

        String bio,

        String avatar,

        String phoneFamilyNumber,

        Boolean isPublic,

        String quote
){
}
