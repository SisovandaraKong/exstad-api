package co.istad.exstadapi.domain;

import co.istad.exstadapi.audit.Auditable;
import co.istad.exstadapi.domain.vo.*;
import co.istad.exstadapi.enums.ProgramLevel;
import co.istad.exstadapi.enums.ProgramType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "programs")
public class Program extends Auditable {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(length = 60, nullable = false, unique = true)
    private String slug;

    @Column(length = 60, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String thumbnailUrl;

    @Column(length = 100)
    private String bgColor;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Highlight> highlights;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<ProgramOverview> programOverviews;

    private String curriculumPdfUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Roadmap> roadmaps;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Faq> faqs;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Requirement> requirements;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<LearningOutcome> learningOutcomes;

    @Enumerated(EnumType.STRING)
    private ProgramType programType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Curriculum> curricula;

    @Enumerated(EnumType.STRING)
    private ProgramLevel programLevel;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "program")
    private List<OpeningProgram> openingPrograms;
}
