package com.dtp.school_management_backend.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;



    @Column(name = "year")
    private int year;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private Member member;

    @OneToMany(mappedBy = "student",
    cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    public Student() {
    }

    public Student(String firstName, String lastName,  int year, Member member, List<Enrollment> enrollments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.member = member;
        this.enrollments = enrollments;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
               ", year=" + year +
                '}';
    }
}

