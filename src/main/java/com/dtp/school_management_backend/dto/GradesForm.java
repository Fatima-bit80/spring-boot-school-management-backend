package com.dtp.school_management_backend.dto;

import java.util.List;

public class GradesForm {

    private String code;
    List<GradeRow> rows;

    public GradesForm() {
    }

    public GradesForm(List<GradeRow> rows) {
        this.rows = rows;
    }

    public List<GradeRow> getRows() {
        return rows;
    }

    public void setRows(List<GradeRow> rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
