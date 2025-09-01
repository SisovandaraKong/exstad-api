package istad.co.exstadbackendapi.features.document;

import io.minio.MinioClient;
import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/documents/upload/{type}")
    public DocumentResponse upload(@PathVariable String type, @RequestPart MultipartFile file) {
        return documentService.uploadDocument(type, file);
    }

    @GetMapping("/public/{filename:.+}")
    public ResponseEntity<Resource> getDocument(@PathVariable String filename) {
        return documentService.previewDocument(filename);
    }

    @GetMapping("/public/{filename:.+}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String filename) {
        return documentService.downloadDocument(filename);
    }
}
