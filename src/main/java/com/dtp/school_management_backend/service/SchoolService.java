package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;

import java.util.List;

public interface SchoolService {


  //accounts:
  Student saveStudent(StudentDTO student);
  void saveTeacher(Teacher teacher);
    Member findMemberByEmail(String email);
  List<Teacher> getAllTeachers();
  StudentDTO findStudentById(int id);


  //courses
  void saveCourse(CourseDTO courseDTO);
  List<Course> findAvailableCourses(int studentId);
  List<Course> findCoursesByTeacherId(int teacherId);


  //enrollments:
    List<EnrollmentDTO> findEnrollmentsOfStudent(int studentId);
  List<Enrollment> findEnrollmentsOfCourse(String code);
  EnrollmentDTO saveEnrollment(String courseCode, int studentId);
  void deleteEnrollment(int studentId,int enrollmentId);
  List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId);
  void acceptEnrollmentRequest(int requestId);
  void updateGrades(GradesForm gradesForm);


}
