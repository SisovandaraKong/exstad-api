package istad.co.exstadbackendapi.features.document;

import istad.co.exstadbackendapi.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByNameAndExtension(String name, String extension);
}
