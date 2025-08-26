package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "opening_program_id")
    private OpeningProgram openingProgram;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private EnrollmentData enrollmentData;

    @Column(nullable = false)
    private Boolean isAccepted;

    @Column(nullable = false)
    private Boolean isPaid;

    @Column(nullable = false)
    private Boolean isAchieved;

    @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AdmissionLetter admissionLetter;
}
