package co.istad.exstadapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum DocumentType {
    CERTIFICATE( "certificate","certificates", List.of("pdf","png","jpg")),
    TRANSCRIPT("transcript","transcripts", List.of("pdf")),
    ACTIVITY( "activity","activities", List.of("pdf")),
    POSTER("poster","images/posters", List.of("jpeg", "jpg", "png", "webp")),
    THUMBNAIL( "thumbnail","images/thumbnails", List.of("jpeg", "jpg", "png", "webp")),
    AVATAR( "avatar","images/avatars", List.of("jpeg", "jpg", "png", "webp"));

    private final String key;
    private final String folderPath;
    private final List<String> supportedFiles;

    public static DocumentType fromKey(String key) {
        for (DocumentType documentType : DocumentType.values()) {
            if (documentType.key.equals(key.toLowerCase())) {
                return documentType;
            }
        }
        throw new IllegalArgumentException("Invalid key: " + key);
    }
}

