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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opening_programs")
public class OpeningProgram {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(mappedBy = "openingProgram")
    private List<Class> classes;

    @OneToMany(mappedBy = "openingProgram")
    private List<Achievement> achievements;
}
