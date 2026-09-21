package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.Course;
import com.dtp.school_management_backend.entity.Student;
import jakarta.persistence.*;

public class EnrollmentDTO {


    private int id;


    private String courseCode;


    private int studentId;

    private Integer grade;

    private Integer approved;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(int id, String courseCode, int studentId, Integer grade, Integer approved) {
        this.id = id;
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.grade = grade;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}

