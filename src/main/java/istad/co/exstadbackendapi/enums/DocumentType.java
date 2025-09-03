package istad.co.exstadbackendapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum DocumentType {
    CERTIFICATE( "certificates", List.of("pdf")),
    TRANSCRIPT("transcripts", List.of("pdf")),
    ACTIVITY( "activities", List.of("pdf")),
    POSTER("images/posters", List.of("jpeg", "jpg", "png", "webp")),
    THUMBNAIL( "images/thumbnails", List.of("jpeg", "jpg", "png", "webp")),
    AVATAR( "images/avatars", List.of("jpeg", "jpg", "png", "webp"));

    private final String folderPath;
    private final List<String> supportedFiles;
}

