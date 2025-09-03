package istad.co.exstadbackendapi.features.document;

import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    DocumentResponse uploadDocument(String type, MultipartFile file);
    ResponseEntity<Resource> previewDocument(String filename);
    ResponseEntity<Resource> downloadDocument(String filename);
    DocumentResponse getDocumentByFileName(String filename);
    List<DocumentResponse> getAllImages();

}
