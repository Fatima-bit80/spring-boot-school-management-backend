package com.dtp.school_management_backend.dto;

public class CourseDTO {

    private String code;

    private String name;

    private int year;


    private int teacherId;

    public CourseDTO() {
    }

    public CourseDTO(String code, String name, int year, int teacherId) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.teacherId = teacherId;
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
}
