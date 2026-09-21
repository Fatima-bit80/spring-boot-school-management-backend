package com.dtp.school_management_backend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "code",nullable = false)
    private Course course;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

    @Column(name = "grade")
    private int grade;

    @Column(name = "approved",nullable = false)
    private int approved;

    public Enrollment() {
    }

    public Enrollment(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.approved = 0;
    }

    public Enrollment(Course course, Student student, int grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
    }

    public Enrollment(Course course, Student student, int grade, int approved) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.approved = approved;
    }

    public Enrollment(int id, Course course, Student student, int grade, int approved) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.approved = approved;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", grade=" + grade +
                '}';
    }
}
