package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.entity.*;

import java.util.List;

public interface CourseService {




//todo service sends and receives dto



  Course saveCourse(Course course);
  List<Course> findAllCourses();
  List<Course> findAvailableCourses(String email);
  List<Course> findCoursesOfStudent(String email);
  List<Course> findCoursesOfTeacher(String email);
  void deleteCourse(String code);
}
