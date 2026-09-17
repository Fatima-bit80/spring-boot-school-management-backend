package com.dtp.school_management_backend.dto;

import com.example.schoolManagement.entity.Admin;
import com.example.schoolManagement.entity.Member;
import com.example.schoolManagement.entity.Student;
import com.example.schoolManagement.entity.Teacher;

public class SchoolDTO {

    private Student student;
    private Member  member;
    private Teacher teacher;
    private Admin admin;

    public SchoolDTO(Member member) {
        this.member = member;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
