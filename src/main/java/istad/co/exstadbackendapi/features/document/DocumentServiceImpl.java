package istad.co.exstadbackendapi.features.document;

import io.minio.*;
import istad.co.exstadbackendapi.domain.Document;
import istad.co.exstadbackendapi.enums.DocumentType;
import istad.co.exstadbackendapi.features.document.dto.DocumentResponse;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;
    @Value("${server.public-access}")
    private String serverUri;

    @Override
    public DocumentResponse uploadDocument(String type, MultipartFile file) {
        if (file.getOriginalFilename() == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }
        DocumentType documentType;
        try{
            documentType = DocumentType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid document type");
        }

        String objectPath = documentType.getFolderPath();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMMdd-HHmmss"));
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String extension = fileName.substring(index);
        fileName = String.format("%s-%s", UUID.randomUUID().toString().replace("-", ""), timestamp);
        if(!validateDocumentType(extension.substring(1), documentType)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file type");
        }
        String objectName = fileName + extension;

        try {
            if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath + "/" + objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        } catch (Exception e) {
            log.error("Cannot connect to Minio", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload document");
        }

        Document document = Document.builder()
                .name(fileName)
                .documentType(documentType)
                .extension(extension.substring(1))
                .fileSize(file.getSize())
                .mimeType(file.getContentType())
                .isDeleted(false)
                .build();
        document = documentRepository.save(document);
        return DocumentResponse.builder()
                .name(document.getName()+ extension)
                .documentType(document.getDocumentType())
                .fileSize(document.getFileSize())
                .mimeType(document.getMimeType())
                .uri(serverUri + objectName)
                .build();

    }

    @Override
    public ResponseEntity<Resource> previewDocument(String filename) {
        Document document = getDocument(filename);

        String folder = document.getDocumentType().getFolderPath();
        String objectPath = folder + "/" + document.getName() + "." + document.getExtension();

        try{
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

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get document");
        }
    }

    @Override
    public ResponseEntity<Resource> downloadDocument(String filename) {
        Document document = getDocument(filename);

        String folder = document.getDocumentType().getFolderPath();
        String objectPath = folder + "/" + document.getName() + "." + document.getExtension();

        try{
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

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get document");
        }
    }

    @Override
    public DocumentResponse getDocumentByFileName(String filename) {
        Document document = getDocument(filename);
        return DocumentResponse.builder()
                .name(document.getName() + "." + document.getExtension())
                .documentType(document.getDocumentType())
                .fileSize(document.getFileSize())
                .mimeType(document.getMimeType())
                .uri(serverUri + document.getName() + "." + document.getExtension())
                .build();
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
                .map(document -> DocumentResponse.builder()
                        .name(document.getName() + "." + document.getExtension())
                        .documentType(document.getDocumentType())
                        .fileSize(document.getFileSize())
                        .mimeType(document.getMimeType())
                        .uri(serverUri + document.getName() + "." + document.getExtension())
                        .build())
                .toList();
    }

    private Document getDocument(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid filename");
        }

        String name = filename.substring(0, lastDotIndex);
        String extension = filename.substring(lastDotIndex + 1);
        return documentRepository.findByNameAndExtension(name, extension).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found")
        );

    }

    private boolean validateDocumentType(String extension, DocumentType documentType) {
        for (String file: documentType.getSupportedFiles()){
            if (extension.equals(file)){
                return true;
            }
        }
        return false;
    }
}
