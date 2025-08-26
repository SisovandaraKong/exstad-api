package istad.co.exstadbackendapi.domain;

import istad.co.exstadbackendapi.audit.Auditable;
import istad.co.exstadbackendapi.domain.vo.*;
import istad.co.exstadbackendapi.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opening_programs")
public class OpeningProgram extends Auditable {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(nullable = false, unique = true)
    private String uuid;

    private String thumbnail;

    @Column(nullable = false)
    private Integer totalSlot;

    @Column(nullable = false)
    private LocalDate startTime;

    @Column(nullable = false)
    private LocalDate endTime;

    private String telegramGroupLink;

    @Column(nullable = false)
    private Integer generation;

    @Column(nullable = false)
    private Boolean isDeleted;

    private Status status;

    @Column(length = 60, nullable = false)
    private String title;

    private String qrCodeUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Detail detail;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Timeline timeline;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Curriculum curriculum;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Roadmap roadmap;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private LearningOutcome learningOutcome;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Requirement requirement;

    private String duration;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Activity activity;

    private String curriculumPdfUri;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "openingProgram")
    private List<Class> classes;

    @OneToMany(mappedBy = "openingProgram")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "openingProgram")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "openingProgram")
    private List<Transcript> transcripts;

    @OneToMany(mappedBy = "openingProgram")
    private List<Certificate> certificates;
}
