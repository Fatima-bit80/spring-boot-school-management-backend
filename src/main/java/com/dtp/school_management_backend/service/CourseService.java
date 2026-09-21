package com.dtp.school_management_backend.service;


import com.dtp.school_management_backend.entity.*;

import java.util.List;

public interface CourseService {








  Course saveCourse(Course course);
  List<Course> findAllCourses(String email);
  List<Course> findAvailableCourses(String email);
  List<Course> findCoursesOfStudent(String email);
  List<Course> findCoursesOfTeacher(String email);





}
