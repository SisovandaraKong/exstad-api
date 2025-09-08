package istad.co.exstadbackendapi.domain;

import istad.co.exstadbackendapi.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admission_letters")
public class AdmissionLetter extends Auditable {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(length = 500)
    private String admissionUrl;


}
