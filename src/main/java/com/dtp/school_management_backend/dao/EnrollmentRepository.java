package com.dtp.school_management_backend.dao;

import com.dtp.school_management_backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {


    @Query( "SELECT e FROM Enrollment e " +
            "JOIN FETCH e.course c " +
            "JOIN FETCH c.teacher " +
            "WHERE e.student.studentId = ?1")
    List<Enrollment> findEnrollmentsForStudent(int studentId);


    @Query("SELECT e FROM Enrollment e " +
            "JOIN FETCH e.student s " +
            "WHERE e.course.code = ?1 AND e.approved = 1")
    List<Enrollment> findEnrollmentsForCourse(String code);


    @Query( "SELECT e FROM Enrollment e " +
            "JOIN FETCH e.course c " +
            "JOIN c.teacher " +
            "JOIN FETCH e.student s " +
            "WHERE c.teacher.teacherId = ?1 AND " +
            "e.approved = 0")
    List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId);

}
