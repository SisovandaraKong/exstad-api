package co.istad.exstadapi.features.enrollment;

import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequest;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequestUpdate;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest);

    List<EnrollmentResponse> getAllEnrollments();
    List<EnrollmentResponse> getAllInterviewedEnrollments();
    List<EnrollmentResponse> getAllPassedEnrollments();
    List<EnrollmentResponse> getAllAchievedEnrollments();
    EnrollmentResponse getEnrollment(String uuid);
    List<EnrollmentResponse> getAllEnrollmentsByOpeningProgramUuid(String openingProgramUuid);
    List<EnrollmentResponse> getAllInterviewedEnrollmentsByOpeningProgramUuid(String openingProgramUuid);
    List<EnrollmentResponse> getAllPassedEnrollmentsByOpeningProgramUuid(String openingProgramUuid);
    List<EnrollmentResponse> getAllAchievedEnrollmentsByOpeningProgramUuid(String openingProgramUuid);
    EnrollmentResponse updateEnrollment(String uuid, EnrollmentRequestUpdate enrollmentRequestUpdate);


}
