package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.Enrollment;
import com.dtp.school_management_backend.entity.Teacher;

import java.util.List;

public class CourseDTO {

    private String code;

    private String name;

    private int year;


    private int teacherId;
    private List<Integer> enrollments;


    public CourseDTO() {
    }

    public CourseDTO(String code, String name, int year, int teacherId, List<Integer> enrollments) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.teacherId = teacherId;
        this.enrollments = enrollments;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<Integer> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Integer> enrollments) {
        this.enrollments = enrollments;
    }
}
