package com.dtp.school_management_backend.dao;

import com.dtp.school_management_backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query( "SELECT c1 " +
            " FROM Course c1" +
            " WHERE c1.code NOT IN(" +
            " SELECT e.course.code FROM Enrollment e " +
            "  WHERE e.student.studentId = ?1)")
    List<Course> findAvailableCoursesForStudent(int studentId);



    @Query( "SELECT t.courses " +
            "FROM Teacher t " +
            "WHERE t.teacherId = ?1")
     List<Course> findCoursesForTeacher(int teacherId);



}
