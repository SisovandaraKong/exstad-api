package istad.co.exstadbackendapi.features.certificate;

import istad.co.exstadbackendapi.domain.Certificate;
import istad.co.exstadbackendapi.domain.OpeningProgram;
import istad.co.exstadbackendapi.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    Optional<Certificate> findByScholarAndOpeningProgram(Scholar scholar, OpeningProgram openingProgram);

    Optional<Certificate> findByScholar(Scholar scholar);
}
