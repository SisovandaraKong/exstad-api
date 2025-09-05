package istad.co.exstadbackendapi.features.document;

import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    DocumentResponse uploadDocument(String offeringType, int gen, String documentType, String filename, MultipartFile file);
    ResponseEntity<Resource> previewDocument(String filename);
    ResponseEntity<Resource> downloadDocument(String filename);
    DocumentResponse getDocumentByFileName(String filename);
    DocumentResponse updateDocumentByFileName(String filename, MultipartFile file);
    List<DocumentResponse> getAllImages();
}
