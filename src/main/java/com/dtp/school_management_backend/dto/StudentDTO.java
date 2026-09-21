package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.*;
import jakarta.persistence.*;

import java.util.List;

public class StudentDTO extends MemberDTO{

    private int studentId;

    private String firstName;

    private String lastName;



    private int year;








    public StudentDTO() {
    }

    public StudentDTO(int studentId, String firstName, String lastName, int year) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
    }

    public StudentDTO(String email, String password, String role, int active, int studentId, String firstName, String lastName, int year) {
        super(email, password, role, active);
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


}
