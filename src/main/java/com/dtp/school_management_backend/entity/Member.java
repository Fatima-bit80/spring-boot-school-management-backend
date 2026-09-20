package com.dtp.school_management_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "email",updatable = false,nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name="active",nullable = false)
    private int active;



    @OneToOne(mappedBy="member")
    private Admin admin;

    @OneToOne(mappedBy="member")
    private Teacher teacher;


    @OneToOne(mappedBy="member")
    private Student student;


    public Member() {
    }

    public Member(String role) {
        this.role = role;
    }

    public Member(String role,int active) {
        this.role = role;
        this.active = active;
    }

    public Member(String password, String role, String email) {
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public Member(String password, String role, int active, String email) {
        this.password = password;
        this.role = role;
        this.active = active;
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Member{" +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                '}';
    }
}
