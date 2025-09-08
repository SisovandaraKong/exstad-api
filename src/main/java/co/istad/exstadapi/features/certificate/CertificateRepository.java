package co.istad.exstadapi.features.certificate;

import co.istad.exstadapi.domain.Certificate;
import co.istad.exstadapi.domain.OpeningProgram;
import co.istad.exstadapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    Optional<Certificate> findByScholarAndOpeningProgram(Scholar scholar, OpeningProgram openingProgram);

    Optional<Certificate> findByScholar(Scholar scholar);
}
