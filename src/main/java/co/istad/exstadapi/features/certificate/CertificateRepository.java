package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.domain.Certificate;
import co.istad.exstadapi.domain.OpeningProgram;
import co.istad.exstadapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {


    @Query("SELECT c FROM Certificate c WHERE c.scholar = :scholar AND c.openingProgram = :openingProgram")
    Optional<Certificate> findByScholarAndOpeningProgram(@Param("scholar") Scholar scholar,
                                                         @Param("openingProgram") OpeningProgram openingProgram);
}

