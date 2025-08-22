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
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 150)
    private String englishName;

    @Column(unique = true, nullable = false, length = 150)
    private String khmerName;

    @Column(length = 150)
    private String shortName;

    @OneToMany(mappedBy = "university")
    private List<Scholar> scholars;
}

