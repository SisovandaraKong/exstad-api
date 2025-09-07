package istad.co.exstadbackendapi.features.document;

import istad.co.exstadbackendapi.domain.Document;
import istad.co.exstadbackendapi.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByNameAndExtension(String name, String extension);
    Optional<Document> findByNameAndDocumentTypeIn(String name, List<DocumentType> types);
    List<Document> findByDocumentTypeIn(List<DocumentType> types);
}
