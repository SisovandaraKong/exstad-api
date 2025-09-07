package istad.co.exstadbackendapi.features.enrollment;

import istad.co.exstadbackendapi.domain.Enrollment;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequest;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequestUpdate;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentResponse;
import istad.co.exstadbackendapi.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);
        enrollment.setUuid(UUID.randomUUID().toString());
        enrollment.setIsAccepted(false);
        enrollment.setIsAchieved(false);
        enrollment.setIsPaid(false);
        enrollment.setIsPassed(false);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.fromEnrollment(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAllByIsAcceptedAndIsAchievedAndIsPassed(false, false, false).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }

    @Override
    public List<EnrollmentResponse> getAllAcceptedEnrollments() {
        return enrollmentRepository.findAllByIsAcceptedAndIsAchievedAndIsPassed(true, false,false ).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }

    @Override
    public List<EnrollmentResponse> getAllPassedEnrollments() {
        return enrollmentRepository.findAllByIsAcceptedAndIsAchievedAndIsPassed(true, false,true ).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }

    @Override
    public List<EnrollmentResponse> getAllAchievedEnrollments() {
        return enrollmentRepository.findAllByIsAchieved(true).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }


    @Override
    public EnrollmentResponse getEnrollment(String uuid) {
        return enrollmentMapper.fromEnrollment(getEnrollmentByUuid(uuid));
    }


    @Override
    public EnrollmentResponse updateEnrollment(String uuid, EnrollmentRequestUpdate enrollmentRequestUpdate) {
        Enrollment enrollment = getEnrollmentByUuid(uuid);
        enrollmentMapper.toEnrollmentPartially(enrollmentRequestUpdate, enrollment);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.fromEnrollment(enrollment);
    }

    private Enrollment getEnrollmentByUuid(String uuid) {
        return enrollmentRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found")
        );
    }
}
