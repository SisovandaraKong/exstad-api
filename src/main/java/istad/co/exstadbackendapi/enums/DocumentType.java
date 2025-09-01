package istad.co.exstadbackendapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentType {
    CERTIFICATE( "certificates"),
    TRANSCRIPT("transcripts"),
    ACTIVITY( "activities"),
    POSTER("images/posters"),
    THUMBNAIL( "images/thumbnails"),
    AVATAR( "images/avatars");

    private final String folderPath;
}

