package com.dtp.school_management_backend.service;

import com.dtp.school_management_backend.entity.Enrollment;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EnrollmentService {

    List<Enrollment> findEnrollments(String email);
    List<Enrollment> findEnrollmentsOfCourse(String code,String email);
    Enrollment saveEnrollment(String courseCode, String email);
    void deleteEnrollment(int enrollmentId, String email);
    Enrollment updateEnrollment(int enrollmentId,Enrollment enrollment,String email);

}
