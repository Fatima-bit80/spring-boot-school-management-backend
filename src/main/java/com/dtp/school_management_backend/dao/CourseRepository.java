package com.dtp.school_management_backend.dao;

import com.dtp.school_management_backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {


    //courses that the student isn't enrolled in
    @Query("SELECT c " +
            " FROM Course c" +
            " WHERE c.code NOT IN(" +
            " SELECT e.course.code " +
            " FROM Enrollment e " +
            "  WHERE e.student.studentId = ?1)")
    List<Course> findAvailableCoursesForStudent(int studentId);

    @Query("SELECT c " +
            "FROM Course c " +
            "WHERE c.code IN( " +
            "SELECT e.course.code " +
            "FROM Enrollment e " +
            "WHERE e.student.studentId = ?1)")
    List<Course> findCoursesOfStudent(int studentId);


    @Query("SELECT t.courses " +
            "FROM Teacher t " +
            "WHERE t.teacherId = ?1")
    List<Course> findCoursesOfTeacher(int teacherId);



}
