package istad.co.exstadbackendapi.features.document;

import io.minio.MinioClient;
import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/{offeringType}/{gen}/{documentType}")
    public DocumentResponse upload(
            @PathVariable String offeringType,
            @PathVariable int gen,
            @PathVariable String documentType,
            @RequestParam(defaultValue = "null", required = false) String filename,
            @RequestPart("file") MultipartFile file)
    {
        return documentService.uploadDocument(offeringType, gen, documentType, filename, file);
    }

    @GetMapping("/{fileName}")
    public DocumentResponse getImageByName(@PathVariable String fileName) {
        return documentService.getDocumentByFileName(fileName);
    }

    @PutMapping("/{fileName}")
    public DocumentResponse updateImageByFileName(@PathVariable String fileName,@RequestPart("file") MultipartFile file) {
        return documentService.updateDocumentByFileName(fileName, file);
    }

    @GetMapping
    public List<DocumentResponse> getAllImages() {
        return documentService.getAllImages();
    }
}
