package istad.co.exstadbackendapi.features.enrollment;

import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequest;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentRequestUpdate;
import istad.co.exstadbackendapi.features.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest);

    List<EnrollmentResponse> getAllEnrollments();
    List<EnrollmentResponse> getAllAcceptedEnrollments();
    List<EnrollmentResponse> getAllPassedEnrollments();
    List<EnrollmentResponse> getAllAchievedEnrollments();
    EnrollmentResponse getEnrollment(String uuid);

    EnrollmentResponse updateEnrollment(String uuid, EnrollmentRequestUpdate enrollmentRequestUpdate);


}
