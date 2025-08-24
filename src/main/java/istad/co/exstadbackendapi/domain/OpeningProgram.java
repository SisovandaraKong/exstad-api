package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opening_programs")
public class OpeningProgram {

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

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    private LocalDate updatedAt;

    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate startTime;

    @Column(nullable = false)
    private LocalDate endTime;

    private String telegramGroupLink;

    @Column(nullable = false)
    private Integer generation;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Boolean status;

    @Column(length = 60, nullable = false)
    private String title;

    private String qrCodeUrl;


    @OneToMany(mappedBy = "openingProgram")
    private List<Class> classes;

    @OneToMany(mappedBy = "openingProgram")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "openingProgram")
    protected List<Enrollment> enrollments;
}
