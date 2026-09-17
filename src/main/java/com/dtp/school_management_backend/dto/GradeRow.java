package com.dtp.school_management_backend.dto;

public class GradeRow {

    private int enrollmentId;
    private int grade;

    public GradeRow() {
    }

    public GradeRow(int enrollmentId, int grade) {
        this.enrollmentId = enrollmentId;
        this.grade = grade;
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

    @Override
    public String toString() {
        return "GradeRow{" +
                "enrollmentId=" + enrollmentId +
                ", grade=" + grade +
                '}';
    }
}
