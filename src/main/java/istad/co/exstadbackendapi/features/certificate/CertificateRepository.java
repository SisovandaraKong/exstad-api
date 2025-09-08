package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.domain.Certificate;
import istad.co.exstadbackendapi.domain.OpeningProgram;
import istad.co.exstadbackendapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {


    @Query("SELECT c FROM Certificate c WHERE c.scholar = :scholar AND c.openingProgram = :openingProgram")
    Optional<Certificate> findByScholarAndOpeningProgram(@Param("scholar") Scholar scholar,
                                                         @Param("openingProgram") OpeningProgram openingProgram);
}

