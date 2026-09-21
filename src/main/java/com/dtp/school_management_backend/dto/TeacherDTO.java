package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.Course;
import com.dtp.school_management_backend.entity.Member;
import jakarta.persistence.*;

import java.util.List;

public class TeacherDTO extends  MemberDTO{


    private int teacherId;

    private String firstName;

    private String lastName;





    public TeacherDTO() {
    }

    public TeacherDTO(int teacherId, String firstName, String lastName) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public TeacherDTO(String email, String password, String role, int active, int teacherId, String firstName, String lastName) {
        super(email, password, role, active);
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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


}
