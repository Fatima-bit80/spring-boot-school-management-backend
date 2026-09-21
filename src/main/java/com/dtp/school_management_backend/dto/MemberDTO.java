package com.dtp.school_management_backend.dto;

import com.dtp.school_management_backend.entity.Admin;
import com.dtp.school_management_backend.entity.Student;
import com.dtp.school_management_backend.entity.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class MemberDTO {



    private String email;

    private String password;

    private String role;

    private int active;





    public MemberDTO() {
    }

    public MemberDTO(String email, String password, String role, int active) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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


}
