package istad.co.exstadbackendapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "opening_program_id")
    private OpeningProgram openingProgram;

    @ManyToOne
    @JoinColumn(name = "scholar_id")
    private Scholar scholar;

    private String tempCertificateUrl;

    @Column(nullable = false)
    private Boolean isVerified;

    private LocalDate verifiedAt;

    private String qrCodeUrl;

    private String certificateUrl;

    @Column(nullable = false)
    private Boolean isDisabled;

    @Column(nullable = false)
    private Boolean isDeleted;
}
