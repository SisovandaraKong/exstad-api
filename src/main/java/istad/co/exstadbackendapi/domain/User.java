package istad.co.exstadbackendapi.domain;

import istad.co.exstadbackendapi.audit.Auditable;
import istad.co.exstadbackendapi.enums.Role;
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
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid; // keycloak uuid

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String englishName;

    @Column(length = 50, nullable = false)
    private String khmerName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Scholar scholar;

    @OneToMany(mappedBy = "verifiedBy")
    private List<ScholarBadge> scholarsBadges;

    @OneToMany(mappedBy = "instructor")
    private List<InstructorClass> instructorClasses;
}
