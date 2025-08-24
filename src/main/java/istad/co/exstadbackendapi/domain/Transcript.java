package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transcripts")
public class Transcript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "opening_program_id")
    private OpeningProgram openingProgram;

    @ManyToOne
    @JoinColumn(name = "scholar_id")
    private Scholar scholar;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranscriptData transcriptData;

    private String qrCodeUrl;

    private String tempTranscriptUrl;

    private String transcriptUrl;

    @Column(nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    private LocalDate updatedAt;

    @Column(nullable = false)
    private Boolean isVerified;

    private LocalDate verifiedAt;
}
