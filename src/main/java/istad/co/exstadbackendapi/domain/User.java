package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Scholar scholar;

    @OneToMany(mappedBy = "verifiedBy")
    private List<ScholarBadge> scholarsBadges;

    @OneToMany(mappedBy = "instructor")
    private List<InstructorClass> instructorClasses;

    @OneToMany(mappedBy = "createdBy")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "createdBy")
    private List<Program> createdPrograms;

    @OneToMany(mappedBy = "updatedBy")
    private List<Program> updatedPrograms;

    @OneToMany(mappedBy = "createdBy")
    private List<AdmissionLetter> admissionLetters;

    @OneToMany(mappedBy = "createdBy")
    private List<OpeningProgram> createdOpeningPrograms;

    @OneToMany(mappedBy = "updatedBy")
    private List<OpeningProgram> updatedOpeningPrograms;
}
