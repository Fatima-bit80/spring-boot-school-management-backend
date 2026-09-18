package com.dtp.school_management_backend.dto;

public class GradeDTO {

    private int enrollmentId;
    private int grade;

    public GradeDTO() {
    }



    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


}
