package istad.co.exstadbackendapi.domain;

import istad.co.exstadbackendapi.enums.Shift;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "opening_program_id")
    private OpeningProgram openingProgram;

    @Column(nullable = false, length = 100)
    private String className;

    @Column(nullable = false)
    private Shift shift;

    @Column(nullable = false)
    private Boolean isWeekend;

    @Column(nullable = false)
    private Integer totalSlot;

    @Column(nullable = false)
    private LocalDate startTime;

    @Column(nullable = false)
    private LocalDate endTime;

    @OneToMany(mappedBy = "aclass")
    private List<InstructorClass> instructorClasses;

    @OneToMany(mappedBy = "aClass")
    private List<ScholarEnrollment> scholarEnrollments;
}
