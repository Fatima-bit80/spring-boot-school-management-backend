package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.dto.*;
import com.dtp.school_management_backend.entity.*;

import java.util.List;

public interface SchoolService {


  //accounts:
  void saveStudent(Student student);
  void saveTeacher(Teacher teacher);
    Member findMemberByEmail(String email);
  List<Teacher> getAllTeachers();


  //courses
  void saveCourse(CourseDTO courseDTO);
  List<Course> findAvailableCourses(int studentId);
  List<Course> findCoursesByTeacherId(int teacherId);


  //enrollments:
    List<Enrollment> findEnrollmentsOfStudent(int studentId);
  List<Enrollment> findEnrollmentsOfCourse(String code);
  void saveEnrollment(String courseCode, int studentId);
  void deleteEnrollment(int id);
  List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId);
  void acceptEnrollmentRequest(int requestId);
  void updateGrades(GradesForm gradesForm);


}
