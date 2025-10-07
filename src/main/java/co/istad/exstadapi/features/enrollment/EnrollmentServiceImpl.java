package co.istad.exstadapi.features.enrollment;

import co.istad.exstadapi.domain.Class;
import co.istad.exstadapi.domain.Enrollment;
import co.istad.exstadapi.features.classes.ClassRepository;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequest;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequestUpdate;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentResponse;
import co.istad.exstadapi.mapper.EnrollmentMapper;
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
    private final ClassRepository classRepository;
    private final EnrollmentMapper enrollmentMapper;


    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
//        if (!classRepository.existsByUuid(enrollmentRequest.classUuid())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found");
//        }
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);
        enrollment.setUuid(UUID.randomUUID().toString());
        enrollment.setIsInterviewed(false);
        enrollment.setIsAchieved(false);
        enrollment.setIsPaid(false);
        enrollment.setIsPassed(false);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.fromEnrollment(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAllByIsInterviewedAndIsAchievedAndIsPassed(false, false, false).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }

    @Override
    public List<EnrollmentResponse> getAllInterviewedEnrollments() {
        return enrollmentRepository.findAllByIsInterviewedAndIsAchievedAndIsPassed(true, false,false ).stream().map(
                enrollmentMapper::fromEnrollment
        ).toList();
    }

    @Override
    public List<EnrollmentResponse> getAllPassedEnrollments() {
        return enrollmentRepository.findAllByIsInterviewedAndIsAchievedAndIsPassed(true, false,true ).stream().map(
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
