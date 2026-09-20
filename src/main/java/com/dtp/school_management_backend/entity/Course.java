package com.dtp.school_management_backend.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id
    @Column(name="code",updatable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name="year")
    private int year;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course",
    cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    public Course() {
    }

    public Course(String code, String name, int year, Teacher teacher, List<Enrollment> enrollments) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
