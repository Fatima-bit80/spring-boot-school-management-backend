package com.dtp.school_management_backend.entity;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id",updatable = false,nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int teacherId;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email",nullable = false)
    private Member member;

    @OneToMany(mappedBy = "teacher",
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName,  Member member, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.member = member;
        this.courses = courses;
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





    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", member=" + member +
                '}';
    }
}

