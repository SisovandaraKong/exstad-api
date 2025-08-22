package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scholars_enrollments")
public class ScholarEnrollment {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "scholar_id")
    private Scholar scholar;

    @Column(nullable = false)
    private Boolean isReminded;

    @Column(nullable = false)
    private Boolean isPaid;
}
