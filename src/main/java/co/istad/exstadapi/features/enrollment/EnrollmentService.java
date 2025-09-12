package co.istad.exstadapi.features.enrollment;

import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequest;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentRequestUpdate;
import co.istad.exstadapi.features.enrollment.dto.EnrollmentResponse;

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
