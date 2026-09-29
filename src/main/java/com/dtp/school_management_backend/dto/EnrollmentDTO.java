package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.Course;
import com.dtp.school_management_backend.entity.Student;
import jakarta.persistence.*;

public class EnrollmentDTO {


    private Integer id;


    private String courseCode;


    private Integer studentId;

    private Integer grade; // can be null

    private Integer approved; // can be null

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(Integer id, String courseCode, Integer studentId, Integer grade, Integer approved) {
        this.id = id;
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.grade = grade;
        this.approved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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


}

