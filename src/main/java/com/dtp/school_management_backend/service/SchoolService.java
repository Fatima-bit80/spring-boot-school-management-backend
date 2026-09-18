package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;

import java.util.List;

public interface SchoolService {


  //accounts:
  StudentDTO saveStudent(StudentDTO student);
  TeacherDTO saveTeacher(TeacherDTO teacher);
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
  void deleteEnrollmentOfStudent(int studentId, int enrollmentId);
  void deleteEnrollmentRequest(int teacherId,int requestId);
  List<EnrollmentDTO> findEnrollmentRequestsForTeacher(int teacherId);
  EnrollmentDTO acceptEnrollmentRequest(int requestId,int teacherId);
  List<EnrollmentDTO> updateGrades(List<GradeDTO> grades,int teacherId);


  TeacherDTO findTeacherById(int teacherId);
}
