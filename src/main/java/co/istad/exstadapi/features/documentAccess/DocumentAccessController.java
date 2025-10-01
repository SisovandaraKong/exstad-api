package co.istad.exstadapi.features.documentAccess;

import co.istad.exstadapi.features.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/documents")
public class DocumentAccessController {
    private final DocumentService documentService;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getDocument(@PathVariable String filename) {
        return documentService.previewDocument(filename);
    }


    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String filename) {
        return documentService.downloadDocument(filename);
    }

}
