package istad.co.exstadbackendapi.features.document;

import io.minio.*;
import istad.co.exstadbackendapi.domain.Document;
import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.enums.OfferingType;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
import istad.co.exstadbackendapi.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final MinioClient minioClient;
    private final DocumentMapper documentMapper;

    @Value("${minio.bucket}")
    private String bucketName;

    private final String FILENAME_KEY = "filename";
    private final String EXTENSION_KEY = "extension";

    @Override
    public DocumentResponse uploadDocument(String offeringType, int gen, String documentType, String preferredFileName, MultipartFile file) {
        if (file.getOriginalFilename() == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }

        DocumentType fileDocumentType = DocumentType.fromKey(documentType);
        OfferingType fileOfferingType = OfferingType.fromKey(offeringType);

        Map<String, String> fileNameAndExtension = getFileNameAndExtension(file.getOriginalFilename());

        String extension = fileNameAndExtension.get(EXTENSION_KEY);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMMdd-HHmmss"));
        String filename = preferredFileName.equals("null") ? String.format("%s-%s", UUID.randomUUID().toString().replace("-", ""), timestamp) : preferredFileName;

        validateDocumentType(fileNameAndExtension.get(EXTENSION_KEY), fileDocumentType);

        String objectPath = getObjectPath(fileOfferingType, gen, fileDocumentType, filename, extension);

        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            log.error("Cannot connect to Minio", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload document");
        }

        Document document = Document.builder()
                .name(filename)
                .offeringType(fileOfferingType)
                .gen(gen)
                .documentType(fileDocumentType)
                .extension(extension)
                .fileSize(file.getSize())
                .mimeType(file.getContentType())
                .isDeleted(false)
                .build();
        document = documentRepository.save(document);
        return documentMapper.fromDocument(document);

    }

    @Override
    public ResponseEntity<Resource> previewDocument(String filename) {
        Document document = getDocument(filename);
        String objectPath = getObjectPath(document.getOfferingType(), document.getGen(), document.getDocumentType(), document.getName(), document.getExtension());

        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .build()
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + document.getName() + "." + document.getExtension() + "\"")
                    .body(new InputStreamResource(stream));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get document");
        }
    }

    @Override
    public ResponseEntity<Resource> downloadDocument(String filename) {
        Document document = getDocument(filename);

        String objectPath = getObjectPath(document.getOfferingType(), document.getGen(), document.getDocumentType(), document.getName(), document.getExtension());

        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .build()
            );

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + document.getName() + "." + document.getExtension() + "\"")
                    .body(new InputStreamResource(stream));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get document");
        }
    }

    @Override
    public DocumentResponse getDocumentByFileName(String filename) {
        return documentMapper.fromDocument(getDocument(filename));
    }

    @Override
    public DocumentResponse updateDocumentByFileName(String filename, MultipartFile file) {
        if (file.getOriginalFilename() == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }

        Document document = getDocument(filename);

        Map<String, String> fileNameAndExtension = getFileNameAndExtension(file.getOriginalFilename());
        if(!fileNameAndExtension.get(EXTENSION_KEY).equals(document.getExtension())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File extension does not match");
        }

        String objectPath = getObjectPath(document.getOfferingType(), document.getGen(), document.getDocumentType(), document.getName(), document.getExtension());

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            log.error("Cannot connect to Minio", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload document");
        }
        document.setUpdatedAt(Instant.now());
        document = documentRepository.save(document);
        return documentMapper.fromDocument(document);
    }

    @Override
    public List<DocumentResponse> getAllImages() {
        List<Document> images = documentRepository.findByDocumentTypeIn(
                List.of(DocumentType.CERTIFICATE,
                        DocumentType.TRANSCRIPT,
                        DocumentType.ACTIVITY,
                        DocumentType.POSTER,
                        DocumentType.THUMBNAIL,
                        DocumentType.AVATAR)
        );

        return images.stream()
                .map(documentMapper::fromDocument)
                .toList();
    }

    private Document getDocument(String filename) {
        Map<String, String> file = getFileNameAndExtension(filename);
        return documentRepository.findByNameAndExtension(file.get(FILENAME_KEY), file.get(EXTENSION_KEY)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found")
        );

    }

    private void validateDocumentType(String extension, DocumentType documentType) {
        for (String file : documentType.getSupportedFiles()) {
            if (extension.equals(file)) {
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid document type");
    }

    private String getObjectPath(OfferingType offeringType, int gen, DocumentType documentType, String filename, String extension) {
        return String.format(
                "%s/%s/%s/%s.%s",
                offeringType.getFolderPath(),
                "gen" + gen,
                documentType.getFolderPath(),
                filename,
                extension.replace(".", "")
        );
    }

    //    This has no dot '.'
    private Map<String, String> getFileNameAndExtension(String originalFileName) {
        int lastDotIndex = originalFileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid filename");
        }
        String name = originalFileName.substring(0, lastDotIndex);
        String extension = originalFileName.substring(lastDotIndex + 1);
        return Map.of(
                FILENAME_KEY, name,
                EXTENSION_KEY, extension
        );
    }
}
