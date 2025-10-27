package co.istad.exstadapi.features.applicantLetter;

import co.istad.exstadapi.features.applicantLetter.dto.ApplicantLetterRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicantLetterService {

    byte[] generateApplicantLetter(ApplicantLetterRequest applicantLetterRequest);
    byte[] generateApplicantLettersZip(List<ApplicantLetterRequest> requests);
}
