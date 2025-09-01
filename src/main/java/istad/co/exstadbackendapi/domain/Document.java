package istad.co.exstadbackendapi.domain;

import istad.co.exstadbackendapi.audit.Auditable;
import istad.co.exstadbackendapi.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document extends Auditable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true, nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(nullable = false, length = 10)
    private String extension;

    @Column(nullable = false, length = 50)
    private String mimeType;

    @Column(nullable = false)
    private Boolean isDeleted;
}
